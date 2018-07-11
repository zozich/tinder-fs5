package ua.danit.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class FreemarkerHandler {
  public static void processTemplate(PrintWriter writer, Map<String, Object> variables, String templateName, Class clazz) throws IOException {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    cfg.setClassLoaderForTemplateLoading(clazz.getClassLoader(), "static/html");
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    cfg.setWrapUncheckedExceptions(true);

    Template template = cfg.getTemplate(templateName);

    try {
      template.process(variables, writer);
    } catch (TemplateException e) {
      e.printStackTrace();
    }
  }
}
