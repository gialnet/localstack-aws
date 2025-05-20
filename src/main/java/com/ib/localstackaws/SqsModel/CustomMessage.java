package com.ib.localstackaws.SqsModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomMessage {
    private String id;
    private String content;
    private String timestamp;
}
