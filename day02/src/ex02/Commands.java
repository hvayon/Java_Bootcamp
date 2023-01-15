package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Commands {
	private Path currentPath;

	public Commands(Path currentPath) throws IOException {
		System.out.println(currentPath);
		if (!Files.exists(currentPath)) {
			throw new IOException("File not found");
		}
		this.currentPath = currentPath;
	}

	public void mv(Path from, Path to) {
		try {
			Path fromAbsolute;
			Path toAbsolute;

			if (from.startsWith("/")) {
				fromAbsolute = Paths.get(from.toString());
			} else {
				fromAbsolute = Paths.get(this.currentPath + "/" + from);
			}
			if (to.startsWith("/")) {
				toAbsolute = Paths.get(to.toString());
			} else {
				toAbsolute = Paths.get(this.currentPath + "/" + to);
			}
			if (Files.isRegularFile(fromAbsolute)) {
				if (Files.isDirectory(toAbsolute)) {
					toAbsolute = Paths.get(toAbsolute + "/" + fromAbsolute.getFileName());
				}
				Files.move(fromAbsolute, toAbsolute, REPLACE_EXISTING);
			} else {
				System.out.println("Wrong file");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void ls() {
		try (Stream<Path> paths = Files.walk(currentPath, 1)) {
			paths
					.filter(path -> !path.getFileName().toString().startsWith(".") && !path.equals(currentPath))
					.forEach(path -> {
						try {
							System.out.println(path.getFileName() + " " +
									(long)Math.ceil((double)(Files.isDirectory(path) ?
											getFolderSize(path.toFile()) : Files.size(path)) / 1024) + " KB");
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private long getFolderSize(File folder) {
		long length = 0;
		File[] files = folder.listFiles();
		if (files == null) {
			return 0;
		}

		int count = files.length;

		for (File file : files) {
			if (file.isFile()) {
				length += file.length();
			} else {
				length += getFolderSize(file);
			}
		}
		return length;
	}

	public void cd(Path to) {
		Path fullPath;
		if (to.startsWith("/")) {
			fullPath = Paths.get(to + "/");
		} else {
			fullPath = Paths.get(currentPath + "/" + to);
		}
		if (Files.exists(fullPath) && Files.isDirectory(fullPath)) {
			currentPath = fullPath.normalize();
			System.out.println(currentPath);
		} else {
			System.out.println("Wrong directory");
		}
	}
}
