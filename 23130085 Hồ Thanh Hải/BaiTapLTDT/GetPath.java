package BaiTapLTDT;

import java.io.File;

public class GetPath {
	
	// Lấy đường dẫn file theo tến
	public static String getPath(String fileName) {
		File projectDir = new File(System.getProperty("user.dir")); // Lấy thư mục gốc của dự án
		File foundFile = findFile(projectDir, fileName);

		return (foundFile != null) ? foundFile.getAbsolutePath() : null;
	}

	private static File findFile(File dir, String fileName) {
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					File found = findFile(file, fileName); // Tìm trong thư mục con
					if (found != null) {
						return found;
					}
				} else if (file.getName().equals(fileName)) {
					return file; // Tìm thấy tệp
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(GetPath.getPath("test2.txt"));
	}
}

