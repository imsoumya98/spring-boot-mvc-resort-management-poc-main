package com.poc.resortmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControllerInfo {
    private String controllerName;
    private List<String> requestMappingUrls;
    private List<String> methodNames;
}
