package com.softpower.chihuahua.core.util.runtime;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import lombok.Cleanup;
import lombok.SneakyThrows;

import com.google.common.base.Charsets;

/**
 * RbRuntime
 * <p>
 * <p>
 */
public class RbRuntime {

	public static class InputStreamConsumer {
		InputStream is;
		PrintStream os;
		TYPE type;

		enum TYPE {
			INPUT, ERROR
		};

		InputStreamConsumer(TYPE type, InputStream is, OutputStream os) {
			this.type = type;
			this.is = is;
			this.os = new PrintStream(os);
		}

		@SneakyThrows(IOException.class)
		public void run() {
			@Cleanup InputStreamReader isr = new InputStreamReader(is, Charsets.UTF_8);
			@Cleanup BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (os != null) {
					os.println(line);
				}
			}
			if (os != null) {
				os.flush();
			}
		}
	}

	@SneakyThrows(Exception.class)
	public static int exec(String args[], OutputStream inputOutputStream, OutputStream errorOutputStream) {
		if (args == null || args.length == 0) {
			return -1;
		}

		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(args);

		InputStreamConsumer errorAsycReader = new InputStreamConsumer(
				InputStreamConsumer.TYPE.ERROR, proc.getErrorStream(),
				errorOutputStream);
		InputStreamConsumer inputAsycReader = new InputStreamConsumer(
				InputStreamConsumer.TYPE.INPUT, proc.getInputStream(),
				inputOutputStream);

		errorAsycReader.run();
		inputAsycReader.run();
		int exitVal = proc.waitFor();

		return exitVal;
	}

	public static int exec(String args[], OutputStream mixOutputStream) {
		return exec(args, mixOutputStream, mixOutputStream);
	}

	public static int execToConsole(String args[]) {
		return exec(args, System.out, System.err);
	}

	@SneakyThrows(IOException.class)
	public static int execToString(String args[], StringBuffer inputSb, StringBuffer errorSb) {
		@Cleanup ByteArrayOutputStream inputBaos = new ByteArrayOutputStream();
		@Cleanup ByteArrayOutputStream errorBaos = new ByteArrayOutputStream();
		int exitVal = exec(args, inputBaos, errorBaos);
		inputSb.append(inputBaos.toString());
		errorSb.append(errorBaos.toString());

		return exitVal;
	}

	@SneakyThrows(IOException.class)
	public static int execToString(String args[], StringBuffer mixSb) {
		@Cleanup ByteArrayOutputStream mixBaos = new ByteArrayOutputStream();
		int exitVal = exec(args, mixBaos, mixBaos);
		mixSb.append(mixBaos.toString());

		return exitVal;
	}

	@SneakyThrows(InterruptedException.class)
	public static void sleep(long millis) {
		Thread.sleep(millis);
	}

}

