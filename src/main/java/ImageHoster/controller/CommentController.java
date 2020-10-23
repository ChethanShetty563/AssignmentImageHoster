package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{id}/{title}/comments",method = RequestMethod.POST)
    public String AddComment(@PathVariable("id") Integer id,@PathVariable("title") String title ,Model model,@RequestParam("comment") String commentText, HttpSession session)
    {
        Image image = imageService.getImage(id);
        User user = (User) session.getAttribute("loggeduser");
        Comment newComment = new Comment();
        newComment.setText(commentText);
        newComment.setCreatedDate(new Date());
        newComment.setUser(user);
        newComment.setImage(image);

        commentService.addComment(newComment);

        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());
        model.addAttribute("comments",image.getComments());
        return "redirect:/images/{id}/{title}";
    }



}
