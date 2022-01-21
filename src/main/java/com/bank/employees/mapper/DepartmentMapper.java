package com.bank.employees.mapper;

import com.bank.employees.dto.DepartmentDto;
import com.bank.employees.entity.Department;
import com.bank.employees.entity.Location;

public class DepartmentMapper{
       public static Department toDepartment(DepartmentDto deptDto){
               if (deptDto == null)
                       return null;

               return Department.builder()
                       .Id(deptDto.getId())
                       .location_id(deptDto.getLocation_id())
                       .deptName(deptDto.getDeptName())
                       .location(LocationMapper.toLocation(deptDto.getLocation()))
                       .build();
       }

        public static DepartmentDto toDepartmentDto(Department dept){
                if (dept == null)
                        return null;

                return DepartmentDto.builder()
                        .Id(dept.getId())
                        .location_id(dept.getLocation_id())
                        .deptName(dept.getDeptName())
                        .location(LocationMapper.toLocationDto(dept.getLocation()))
                        .build();
        }
}
