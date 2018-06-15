package com.mrbt.utils.mybatis;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * MyBatis Generator plugin to give query Example classes a common root class.
 *
 * This plugin accepts one properties:
 * <ul>
 * <li><tt>exampleRootClass</tt> (required) the fully qualified class name for
 * the base class</li>
 * For example, to use foo.bar.ExampleBase as a base class: <code>
 * <generatorConfiguration>
    <classPathEntry location="/path/to/MyBatisExampleRootPlugin.jar" />
    <context id="context1" >
      <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin" />
      <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
      <plugin type="nonet.mybatis.generator.plugins.ExampleClassRoot">
         <property name="exampleRootClass" value="foo.bar.ExampleBase" />
      </plugin>
      .
      .
   </code> To run: java -classpath
 * mybatis-generator-core-1.3.1.jar:MyBatisExampleRootPlugin.jar
 * org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml
 * -overwrite -verbose
 * 
 * @author Donald Munro
 */
public class MybatisExampleRootPlugin extends PluginAdapter
// =================================================
{
	private String baseClassName;

	public MybatisExampleRootPlugin() {
	}

	@Override
	public boolean validate(List<String> warnings)
	// --------------------------------------------
	{
		baseClassName = properties.getProperty("exampleRootClass");
		boolean isValid = stringHasValue(baseClassName);
		if (!isValid)
			warnings.add("ExampleClassRoot plugin: exampleRootClass property not found");
		return isValid;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	// ---------------------------------------------------------------------------------------------------------
	{
		if (baseClassName != null) {
			FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(
					baseClassName);
			topLevelClass.addImportedType(superClass);
			topLevelClass.setSuperClass(superClass);
			System.out.println("Setting Example class "
					+ topLevelClass.getType().getFullyQualifiedName()
					+ " super class to " + superClass.getFullyQualifiedName());
		}
		return true;
	}
}