package org.apache.torque.mojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

/**
 * This takes a schema in the format needed by the Ant version of Impex and
 * converts it to the format needed by the Maven Impex Plugin
 */
public class SchemaMorpher {
	// Ant impex has kfs hard coded
	String defaultSchemaName = "kfs";
	// Ant impex is hard coded to database.dtd
	String defaultDTDString = "\"database.dtd\"";
	// The Kuali database.dtd
	String newDTDString = "\"http://www.kuali.org/dtd/database.dtd\"";
	// Ant impex comment
	String defaultComment = "<!-- Autogenerated by KualiTorqueJDBCTransformTask! -->";
	// Ant impex comment
	String newComment = "<!-- Autogenerated by the Maven Impex Plugin -->";

	String encoding = "UTF-8";

	protected String morph(String contents, String schemaName) {
		contents = StringUtils.replace(contents, defaultDTDString, newDTDString);
		contents = StringUtils.replace(contents, defaultComment, newComment);
		return StringUtils.replace(contents, "name=\"" + defaultSchemaName + "\">", "name=\"" + schemaName + "\">");
	}

	/**
	 * 
	 * @param content
	 * @return
	 */
	protected boolean isMorphNeeded(String content) {
		// Look for the DTD the Maven Impex Plugin uses
		int pos = content.indexOf(newDTDString);

		if (pos == -1) {
			// It isn't there so we should morph
			return true;
		} else {
			// It is already there, we are good to go
			return false;
		}
	}

	/**
	 * Morph the schema.xml file
	 */
	public void morphSchema(MavenProject project, Log log) {
		try {
			String filename = project.getBasedir().getAbsolutePath() + "/src/main/impex/schema.xml";
			String newDir = project.getBuild().getDirectory() + "/generated-impex";
			FileUtils.forceMkdir(new File(newDir));
			String newFilename = new File(newDir).getAbsolutePath() + "/" + project.getArtifactId() + "-schema.xml";
			File file = new File(filename);
			String contents = getContents(file);
			if (isMorphNeeded(contents)) {
				log.info("Morphing schema for " + project.getArtifactId());
				contents = morph(contents, project.getArtifactId());
			} else {
				log.info("No morph needed for " + project.getArtifactId());
			}
			IOUtils.write(contents, new FileOutputStream(newFilename), encoding);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String getContents(File file) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			return IOUtils.toString(in, encoding);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
