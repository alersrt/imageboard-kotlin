package me.alersrt.imageboard.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RootController {

    @GetMapping
    fun getRoot(): ResponseEntity<RootResponse> = ResponseEntity.ok(RootResponse(""))

    data class RootResponse(var text: String? = "DEFAULT")
}