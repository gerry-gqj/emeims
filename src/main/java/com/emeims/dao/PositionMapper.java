package com.emeims.dao;

import com.emeims.entity.Position;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionMapper {

    List<Position> getAllPosition();

}
