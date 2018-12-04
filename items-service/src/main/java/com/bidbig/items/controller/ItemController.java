package com.bidbig.items.controller;

import com.bidbig.items.dto.ItemDto;
import com.bidbig.items.dto.User;
import com.bidbig.items.entity.Item;
import com.bidbig.items.payload.UploadImageResponse;
import com.bidbig.items.service.ImageStorageService;
import com.bidbig.items.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
public class ItemController {
    @Autowired
    ImageStorageService imageStorageService;
    @Autowired
    ItemService itemService;
    @Autowired
    ObjectMapper objectMapper;

    @Transactional
    @RequestMapping(value = "/item", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestPart("item") ItemDto itemDto,
                                    @RequestPart("image")
                                    @Valid @NotBlank @NotNull MultipartFile image) {
        String fileName = imageStorageService.storeImage(image, itemDto.getUserId());
        String imageDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadImage/")
                .path(fileName)
                .toUriString();

        itemService.createItem(itemDto, fileName);

        UploadImageResponse imageResponse = new UploadImageResponse(fileName, imageDownloadUri, image.getContentType(), image.getSize());
        return ResponseEntity.ok(imageResponse);
    }

    @Transactional
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("itemId") String itemId, @RequestBody @Valid ItemDto itemDto,
                @RequestParam(value = "image", required = false) MultipartFile image) {
        String fileName = imageStorageService.storeImage(image, itemDto.getUserId());
        String imageDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadImage/")
                .path(fileName)
                .toUriString();
        itemService.updateItem(Integer.parseInt(itemId), itemDto, fileName);

        UploadImageResponse imageResponse = new UploadImageResponse(fileName, imageDownloadUri, image.getContentType(), image.getSize());
        return ResponseEntity.ok(imageResponse);
    }

    @Transactional
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("itemId") String itemId) throws JsonProcessingException {
        if(itemService.deleteItem(Integer.parseInt(itemId))) {
            return ResponseEntity.noContent().build();
        } else {
            String message = String.format("Item (ItemId: %s) cannot be deleted because the status for this item is Auctioned.", itemId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(objectMapper.writeValueAsString(message));
        }

    }

    // @Transactional
    @RequestMapping(value = "/page/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllItems(@PathVariable("userId") String userId, Pageable pageable) {
        Page<Item> results = itemService.getPageOfItems(Integer.parseInt(userId), pageable);
        return ResponseEntity.ok(results);
    }

    @RequestMapping(value = "/downloadImage/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadImage(@PathVariable String fileName, HttpServletRequest request) {
        // Load image as resource
        Resource resource = imageStorageService.loadImageAsResource(fileName);

        // Determine image's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
