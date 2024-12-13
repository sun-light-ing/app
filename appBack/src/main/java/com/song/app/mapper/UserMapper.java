package com.song.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.app.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 