package org.sejapoe.videomanager.controller

import org.sejapoe.videomanager.dto.user.CreateLecturerReq
import org.sejapoe.videomanager.mapper.UserMapper
import org.sejapoe.videomanager.model.User
import org.sejapoe.videomanager.security.annotations.IsAdmin
import org.sejapoe.videomanager.security.annotations.IsUser
import org.sejapoe.videomanager.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userMapper: UserMapper,
    private val userService: UserService,
) {
    @IsUser
    @GetMapping("/user")
    fun getUser(@AuthenticationPrincipal user: User) = userMapper.toUserRes(user)

    @IsAdmin
    @PostMapping("/users")
    fun createLecturer(@RequestBody createLecturerReq: CreateLecturerReq) =
        userService.createLecturer(createLecturerReq.name, createLecturerReq.email).let(userMapper::toUserRes)

    @IsAdmin
    @GetMapping("/users")
    fun getAllLecturers() = userService.getLecturers().map(userMapper::toUserRes)
}