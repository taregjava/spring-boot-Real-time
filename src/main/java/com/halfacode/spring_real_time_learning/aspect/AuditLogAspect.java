package com.halfacode.spring_real_time_learning.aspect;

import com.halfacode.spring_real_time_learning.model.AuditLogEntry;
import com.halfacode.spring_real_time_learning.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditLogAspect {

    private final AuditLogService auditLogService;

    // 🔸 Pointcut to match ALL public methods in FileController
    @Pointcut("execution(public * com.halfacode.spring_real_time_learning.controller.FileController.*(..))")
    public void fileControllerMethods() {}

    // 🔹 Log after successful return
    @AfterReturning(pointcut = "fileControllerMethods()", returning = "response")
    public void logAllControllerMethods(JoinPoint joinPoint, Object response) {

        String methodName = joinPoint.getSignature().getName();
        String path = "/api" + extractPathFromMethodName(methodName);

        Object[] args = joinPoint.getArgs();
        String userId = extractUserId(args);
        Object requestPayload = extractRequestPayload(args);

        AuditLogEntry logEntry = new AuditLogEntry();
        logEntry.setEvent(methodName);
        logEntry.setUserId(userId);
        logEntry.setPath(path);
        logEntry.setTimestamp(LocalDateTime.now().toString());
        logEntry.setDetails("Method " + methodName + " called");
        logEntry.setRequestPayload(requestPayload);
        logEntry.setResponsePayload(response); // ✅ Add response here

        auditLogService.logEntry(logEntry);
    }


    private String extractUserId(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg instanceof String)
                .map(Object::toString)
                .findFirst()
                .orElse("unknown");
    }

    private Object extractRequestPayload(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof List<?> list && !list.isEmpty() && list.get(0) instanceof MultipartFile) {
                List<Object> fileSummaries = new ArrayList<>();
                for (Object file : list) {
                    MultipartFile multipartFile = (MultipartFile) file;
                    fileSummaries.add(Map.of(
                            "fileName", multipartFile.getOriginalFilename(),
                            "contentType", multipartFile.getContentType(),
                            "size", multipartFile.getSize()
                    ));
                }
                return fileSummaries;
            } else if (!(arg instanceof String)) {
                // For JSON bodies like UserSubmissionDto
                return arg;
            }
        }
        return null;
    }


    // 🔸 Try to extract full request payload if available
  /*  private Object extractRequestPayload(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> !(arg instanceof String)) // ignore string params like userId
                .findFirst()
                .orElse(null);
    }
*/
    // 🔸 You can improve this to map paths more dynamically if you want
    private String extractPathFromMethodName(String methodName) {
        return switch (methodName) {
            case "uploadTempFiles" -> "/upload-temp";
            case "getTempFiles" -> "/confirmation/{userId}";
            case "submitFinalRequest" -> "/submit";
            case "checkRedisKey" -> "/check";
            default -> "";
        };
    }
}



/* @AfterReturning(pointcut = "execution(* com.halfacode.spring_real_time_learning.controller.FileController.submitFinalRequest(..))", returning = "response")
    public void logSubmitRequest(JoinPoint joinPoint, Object response) {

        Object[] args = joinPoint.getArgs();

        if (args.length > 0 && args[0] instanceof com.halfacode.spring_real_time_learning.dto.UserSubmissionDto userInfo) {

            String userId = userInfo.getUserId();

            AuditLogEntry logEntry = new AuditLogEntry();
            logEntry.setEvent("UserSubmitted");
            logEntry.setUserId(userId);
            logEntry.setPath("/submit");
            logEntry.setTimestamp(LocalDateTime.now().toString());
            logEntry.setDetails("User form submitted");
            logEntry.setRequestPayload(userInfo);

            auditLogService.logEntry(logEntry);

            // You can log more here, like the response object if you want
        }*/