package com.song.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.app.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}

