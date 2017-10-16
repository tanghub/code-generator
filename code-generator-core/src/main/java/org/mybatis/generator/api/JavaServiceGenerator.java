/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.api;

import freemarker.template.Template;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JavaServiceGenerator {

    private Logger logger = LoggerFactory.getLogger(JavaServiceGenerator.class);

    private Configuration configuration;

    JavaServiceGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    private List<JavaServiceTemplate> generateTemplateDataList() {
        List<Context> contexts = configuration.getContexts();
        List<JavaServiceTemplate> templateList = new ArrayList<JavaServiceTemplate>();
        String curPath = System.getProperty("user.dir");

        for (Context ctx : contexts){
            JavaServiceGeneratorConfiguration jgc = ctx.getJavaServiceGeneratorConfiguration();
            String templatePath = ctx.getJavaServiceGeneratorConfiguration().getTemplatePath();
            List<TableConfiguration> tableConfigurations = ctx.getTableConfigurations();
            for (TableConfiguration table : tableConfigurations) {

                //logger.info(JavaBeansUtil.getCamelCaseString(table.getGeneratedKey().getColumn(), false));
                String tbName = table.getDomainObjectName();
                JavaServiceTemplate template = new JavaServiceTemplate();

                if (templatePath != null && !templatePath.isEmpty()) {
                    template.setTemplatePath(templatePath);
                } else {
                    template.setTemplatePath("default:/template");
                }

                if (table.getGeneratedKey() != null) {
                    template.setPrimaryKeyName(JavaBeansUtil.getCamelCaseString(table.getGeneratedKey().getColumn(), false));
                    template.setPrimaryKeyType(table.getGeneratedKey().getType());
                }
                template.setInterfaceName(tbName + "Service");
                template.setServiceName(tbName + "ServiceImpl");
                template.setMapperClzName(tbName + "Mapper");
                template.setMapperDefineName(lowerCamelCase(tbName) + "Mapper");
                template.setModelClzName(tbName);
                template.setModelDefineName(lowerCamelCase(tbName));
                template.setModelPackage(ctx.getJavaModelGeneratorConfiguration().getTargetPackage());
                template.setMapperPackage(ctx.getJavaClientGeneratorConfiguration().getTargetPackage());
                String servicePath = jgc.getTargetProject()+"/"+jgc.getTargetPackage().replaceAll("\\.","/")+"/";
                template.setServicePath(servicePath);
                template.setProjectPath(curPath);
                template.setServicePackage(jgc.getTargetPackage());
                templateList.add(template);
            }
        }
        return templateList;
    }

    private String lowerCamelCase(String source) {

        return source.isEmpty() ? "" :
                Character.toLowerCase(source.charAt(0)) + source.substring(1);
    }

    /**
     * 通过freemarker生成接口和servcie实现
     */
    public void generate() {
        List<JavaServiceTemplate> templateList = generateTemplateDataList();
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        try  {
            String templatePath = "";
            for (JavaServiceTemplate template : templateList) {
                templatePath = template.getTemplatePath();
                if (templatePath.indexOf("default:") == 0) {
                    cfg.setClassForTemplateLoading(JavaServiceGenerator.class, templatePath.split(":")[1]);
                } else if (templatePath.indexOf(".") == 0) {
                    cfg.setDirectoryForTemplateLoading(new File(template.getProjectPath() + File.separator + templatePath.split(".")[1]));
                } else {
                    cfg.setDirectoryForTemplateLoading(new File(templatePath));

                }
                Template interfaceTmp = cfg.getTemplate("interfaceTemplate.ftl");

                String serviceDirPath = template.getProjectPath() + File.separator + template.getServicePath();

                File fileDir = new File(serviceDirPath);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }

                File interfaceFile = new File( serviceDirPath + File.separator + template.getInterfaceName() + ".java");

                FileWriter interfaceFw = new FileWriter(interfaceFile);

                interfaceTmp.process(template, interfaceFw);

                Template serviceTmp = cfg.getTemplate("serviceTemplate.ftl");
                serviceDirPath += File.separator + "impl";
                File implDir = new File(serviceDirPath);
                if (!implDir.exists()) {
                    implDir.mkdirs();
                }
                File serviceFile = new File(serviceDirPath + File.separator + template.getServiceName() + ".java");
                FileWriter serviceFw = new FileWriter(serviceFile);
                serviceTmp.process(template, serviceFw);

            }

        } catch (Exception e) {
            logger.error("service generate error", e);
        }
    }
}
