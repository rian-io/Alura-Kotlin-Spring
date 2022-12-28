package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewQuestionForm
import br.com.alura.forum.dto.QuestionView
import br.com.alura.forum.dto.UpdateQuestionForm
import br.com.alura.forum.service.QuestionService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("questions")
class QuestionController(
    private val service: QuestionService
) {
    @GetMapping
    @Cacheable("questionsCache")
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<QuestionView> {
        return service.list(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): QuestionView {
        return service.getById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict("questionsCache", allEntries = true)
    fun register(
        @RequestBody @Valid form: NewQuestionForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<QuestionView> {
        val questionView = service.register(form)
        var uri = uriBuilder.path("/topicos/${questionView.id}").build().toUri()

        return ResponseEntity.created(uri).body(questionView)
    }

    @PutMapping
    @Transactional
    @CacheEvict("questionsCache", allEntries = true)
    fun update(@RequestBody @Valid form: UpdateQuestionForm): ResponseEntity<QuestionView> {
        return ResponseEntity.ok(service.update(form))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict("questionsCache", allEntries = true)
    fun delete(@PathVariable id:Long) {
        service.delete(id)
    }
}