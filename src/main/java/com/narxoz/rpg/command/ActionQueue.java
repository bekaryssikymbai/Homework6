package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {
    private List<ActionCommand> queue;

    public ActionQueue() {
        this.queue = new ArrayList<>();
    }

    public void enqueue(ActionCommand command) {
        queue.add(command);
    }

    public void undoLast() {
        if (!queue.isEmpty()) {
            queue.remove(queue.size() - 1);
        }
    }

    public void executeAll() {
        for (ActionCommand command : queue) {
            command.execute();
        }
        queue.clear();
    }

    public List<String> getCommandDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (ActionCommand command : queue) {
            descriptions.add(command.getDescription());
        }
        return descriptions;
    }

    public int size() {
        return queue.size();
    }
}