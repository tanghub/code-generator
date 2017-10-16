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
package org.mybatis.generator.config;

public class JavaServiceTemplate {
    /** 项目所在路径 */
    private String projectPath;
    /** 自定义模版文件路径 */
    private String templatePath;
    /** 包名（接口所在包，实现类将自动放置在impl下 */
    private String servicePackage;
    /** model类 import 名 */
    private String modelPackage;
    private String mapperPackage;
    private String interfaceName;
    private String serviceName;
    private String mapperClzName;
    /** mapper变量名 */
    private String mapperDefineName;
    private String modelClzName;
    /** model变量名 */
    private String modelDefineName;
    /** service 文件夹相对路径 */
    private String servicePath;
    /** 主键在model的命名 */
    private String primaryKeyName;
    /** 主键的类型 */
    private String primaryKeyType;

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMapperClzName() {
        return mapperClzName;
    }

    public void setMapperClzName(String mapperClzName) {
        this.mapperClzName = mapperClzName;
    }

    public String getMapperDefineName() {
        return mapperDefineName;
    }

    public void setMapperDefineName(String mapperDefineName) {
        this.mapperDefineName = mapperDefineName;
    }

    public String getModelClzName() {
        return modelClzName;
    }

    public void setModelClzName(String modelClzName) {
        this.modelClzName = modelClzName;
    }

    public String getModelDefineName() {
        return modelDefineName;
    }

    public void setModelDefineName(String modelDefineName) {
        this.modelDefineName = modelDefineName;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public String getPrimaryKeyType() {
        return primaryKeyType;
    }

    public void setPrimaryKeyType(String primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
    }
}
