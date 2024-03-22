package TasteMates.DiverseDish.comment_review.comment;

import TasteMates.DiverseDish.comment_review.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("recipe/{recipeId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     */
    @PostMapping
    public String createComment(
            @PathVariable("recipeId")
            Long recipeId,
            @RequestParam("content")
            String content,
            Authentication authentication,
            Model model
    ) {
        String username = authentication.getName();

        CommentDto comment = commentService.createComment(recipeId, username, content);

        model.addAttribute("comment", comment);
        return "redirect:/recipe/%d".formatted(recipeId);
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("{commentId}")
    public String deleteComment(
            @PathVariable("recipeId")
            Long recipeId,
            @PathVariable("commentId")
            Long commentId,
            Authentication authentication
    ) {
        String username = authentication.getName();

        commentService.deleteComment(recipeId, commentId, username);

        return "redirect:/recipe/%d".formatted(recipeId);
    }

}
