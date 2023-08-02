package com.example.market.controller;

import com.example.market.dto.comment.CommentDTO;
import com.example.market.dto.comment.CommentResponseDTO;
import com.example.market.dto.ResponseDTO;
import com.example.market.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/items/{itemId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseDTO create(@PathVariable("itemId") Long itemId, @RequestBody @Valid CommentDTO commentDTO, Authentication authentication) {
        commentService.saveComment(itemId, commentDTO, authentication.getName());
        return new ResponseDTO("댓글이 등록되었습니다.");
    }

    @GetMapping
    public Page<CommentResponseDTO> readAll(@PathVariable("itemId") Long itemId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "0") Integer limit) {
        return commentService.readAllComment(itemId, page, limit);
    }

    @PutMapping("/{commentId}")
    public ResponseDTO update(@PathVariable("itemId") Long itemId, @PathVariable("commentId") Long commentId, @RequestBody @Valid CommentDTO commentDTO, Authentication authentication) {
        commentService.updateComment(itemId, commentId, commentDTO, authentication.getName());
        return new ResponseDTO("댓글이 수정되었습니다.");
    }

    @PutMapping("/{commentId}/reply")
    public ResponseDTO updateReply(@PathVariable("itemId") Long itemId, @PathVariable("commentId") Long commentId, @RequestBody @Valid CommentDTO commentDTO, Authentication authentication) {
        commentService.updateReply(itemId, commentId, commentDTO, authentication.getName());
        return new ResponseDTO("댓글에 답변이 추가되었습니다.");
    }

    @DeleteMapping("/{commentId}")
    public ResponseDTO delete(@PathVariable("itemId") Long itemId, @PathVariable("commentId") Long commentId, Authentication authentication) {
        commentService.deleteComment(itemId, commentId, authentication.getName());
        return new ResponseDTO("댓글을 삭제했습니다.");
    }

}
