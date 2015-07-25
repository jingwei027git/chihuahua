package com.softpower.chihuahua.core.util.runtime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import lombok.Cleanup;
import lombok.SneakyThrows;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.io.Files;
import com.softpower.chihuahua.core.util.runtime.RbRuntime;
import com.softpower.chihuahua.test.GenericTest;


public class RbRuntimeTest extends GenericTest {

	@Test
	public void testExecToConsole() {
		String [] args = new String[] {"cmd.exe", "/C", "dir"};
		RbRuntime.execToConsole(args);
	}

	@Test
	public void execToString() {
		String [] args = new String[] {"cmd.exe", "/C", "dir"};
		StringBuffer mixSb = new StringBuffer();
		RbRuntime.execToString(args, mixSb);
		Assert.assertTrue(mixSb.length() > 0);
	}

	@SneakyThrows(IOException.class)
	@Test
	public void execToFileOutputStream() {
		String [] args = new String[] {"cmd.exe", "/C", "dir"};
		File tmpDir = Files.createTempDir();
		File tmpFile = new File(tmpDir, UUID.randomUUID().toString());
		@Cleanup FileOutputStream fos = new FileOutputStream(tmpFile);
		RbRuntime.exec(args, fos);
		tmpFile.delete();
	}

}
