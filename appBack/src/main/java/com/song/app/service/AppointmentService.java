package com.song.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.song.app.entity.Appointment;

public interface AppointmentService {
    // 创建预约
    boolean createAppointment(Appointment appointment);
    
    // 更新预约状态
    boolean updateStatus(Long id, Integer status);
    
    // 取消预约
    boolean cancelAppointment(Long id);
    
    // 分页查询用户的预约列表
    IPage<Appointment> getUserAppointments(Long userId, Integer page, Integer size);
    
    // 分页查询所有预约（管理员使用）
    IPage<Appointment> getAllAppointments(Integer page, Integer size);
    
    // 获取预约详情
    Appointment getAppointmentById(Long id);
} 