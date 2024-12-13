package com.song.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.song.app.entity.Appointment;
import com.song.app.mapper.AppointmentMapper;
import com.song.app.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper appointmentMapper;

    @Override
    @Transactional
    public boolean createAppointment(Appointment appointment) {
        appointment.setStatus(0); // 设置初始状态为待确认
        return appointmentMapper.insert(appointment) > 0;
    }

    @Override
    @Transactional
    public boolean updateStatus(Long id, Integer status) {
        Appointment appointment = appointmentMapper.selectById(id);
        if (appointment != null) {
            appointment.setStatus(status);
            return appointmentMapper.updateById(appointment) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean cancelAppointment(Long id) {
        return updateStatus(id, 2); // 状态2表示已取消
    }

    @Override
    public IPage<Appointment> getUserAppointments(Long userId, Integer page, Integer size) {
        Page<Appointment> pageParam = new Page<>(page, size);
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                   .orderByDesc("create_time");
        return appointmentMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public IPage<Appointment> getAllAppointments(Integer page, Integer size) {
        Page<Appointment> pageParam = new Page<>(page, size);
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return appointmentMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentMapper.selectById(id);
    }
} 