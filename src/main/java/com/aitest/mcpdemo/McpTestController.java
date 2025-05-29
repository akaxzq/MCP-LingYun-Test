package com.aitest.mcpdemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author XZQ
 * @Date 2025/5/25 16:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai/v1")
public class McpTestController {
    private final ChatModel chatModel;

    private final ToolCallbackProvider tools;

    public McpTestController(ChatModel chatModel,ToolCallbackProvider tools) {
        this.chatModel = chatModel;
        this.tools = tools;
    }

    @GetMapping("/mcpChat")
    public String mcpChat(){
//        无MCP时
//        ChatClient mcpClient=ChatClient.builder(chatModel).build();
//        存在MCP时
        ChatClient mcpClient=ChatClient.builder(chatModel).defaultTools(tools).build();
        return mcpClient.prompt(new Prompt("怎么到达北京短波通信局？")).call().content();
    }
}
