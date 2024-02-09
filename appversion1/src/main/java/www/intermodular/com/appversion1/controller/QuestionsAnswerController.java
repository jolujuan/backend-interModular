package www.intermodular.com.appversion1.controller;

import org.springframework.web.bind.annotation.RestController;

import www.intermodular.com.appversion1.model.dto.PaginaDto;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswerInfo;
import www.intermodular.com.appversion1.model.dto.QuestionAnswerList;
import www.intermodular.com.appversion1.service.QuestionsAnswerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


@RestController
@RequestMapping("/api/v1")
public class QuestionsAnswerController {
    private final QuestionsAnswerService questionsAnswerService;
    public QuestionsAnswerController(QuestionsAnswerService questionsAnswerService){
        this.questionsAnswerService= questionsAnswerService;
    }

     @GetMapping("/questions")
    public ResponseEntity <Map<String,Object>> getAllQuestions(
        @RequestParam(defaultValue =  "0") int page,
        @RequestParam(defaultValue =  "3") int size,
        @RequestParam(defaultValue =  "idPregunta,asc") String[] sort) {

       
       try {
        List<Order> criteriosOrdenacion= new ArrayList<Order>();
        if (sort[0].contains(",")) {
            for (String criterioOrdenacion : sort) {
                String[] orden = criterioOrdenacion.split(",");
                if (orden.length > 1) 
                    criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]),orden[0])); 
                       else 
                       criteriosOrdenacion.add(new Order(Direction.fromString("asc"),orden[0]));
                   
            }
        }else{
            criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]),sort[0]));
        }

             Sort sorts= Sort.by(criteriosOrdenacion);

             Pageable paging = PageRequest.of(page,size,sorts);
            PaginaDto<QuestionAnswerList> paginaQuestionsList=questionsAnswerService.findAllPageQuestionAnswerList(paging);

        List<QuestionAnswerList> questions = paginaQuestionsList.getContent();
        Map<String, Object> response = new HashMap();

        response.put("data", questions);
        response.put("currentPage", paginaQuestionsList.getNumber());
        response.put("pageSize", paginaQuestionsList.getSize());
        response.put("totalItems", paginaQuestionsList.getTotalElements());
        response.put("totalPages", paginaQuestionsList.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
            
    
}
    @GetMapping("/questions/category/{category}")
    public ResponseEntity<Map<String, Object>> getRandomQuestionByCategory(@PathVariable String category) {

        try {
        Map<String, Object> response = new HashMap<>();
        response.put("question", questionsAnswerService.getRandomQuestionByCategory(category));
        return ResponseEntity.ok(response);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
