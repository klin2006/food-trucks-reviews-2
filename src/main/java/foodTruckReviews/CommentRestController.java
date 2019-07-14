package foodTruckReviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping	("/tags")

public class CommentRestController {
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@RequestMapping("")
			public Iterable<Comment>findAllComments(){
				return commentRepo.findAll();
			}
	
	@RequestMapping("/{id}")
	public Optional<Comment>findOneTag(@PathVariable long id){
		return commentRepo.findById(id);
	}
	
	@RequestMapping("/{commentComment}/foodtrucks")
	public Collection<Comment> findAllCommentsByReview(@PathVariable(value = "commentComment") String commentComment){
	Comment comment = commentRepo.findByTypeIgnoreCaseLike(commentComment);
		return reviewRepo.findByCommentsContains(comment); 
	}
}