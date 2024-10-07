package com.br.api.v1.listener;

import com.br.api.v1.model.DepartmentModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DepartmentListener {
    @RabbitListener(queues = "department-user")
    public void onDepartmentCreated(DepartmentModel departmentModel) {
        System.out.println("departamento recebido:" + departmentModel.getNome());
    }
}
