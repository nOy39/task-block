package org.a2lpo.taskblock.utils;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.payload.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskUtils {

    private TaskResponse swap(TaskResponse taskResponse, Task task) {
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setColor(task.getColor());
        taskResponse.setImageUrl(task.getImageUrl());
        taskResponse.setCreated(task.getCreated());
        taskResponse.setExpiredDate(task.getExpiredDate());
        taskResponse.setIsDone(task.getIsDone());
        return taskResponse;
    }

    public List<TaskResponse> createResponseList(List<Task> taskList, ArrayList<TaskResponse> responseList) {
        for (Task task : taskList) {
            if (task.getParentId() == null) {
                responseList.add(swap(new TaskResponse(), task));
            } else {
                responseList.add(swap(new TaskResponse(
                        task.getParentId().getId(),
                        task.getParentId().getName()), task));
            }
        }
        return responseList;
    }
}
