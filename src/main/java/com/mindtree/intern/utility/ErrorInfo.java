package com.mindtree.intern.utility;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {
private Integer errorCode;
private String errorMessage;
private LocalDate timeStamp;

}
