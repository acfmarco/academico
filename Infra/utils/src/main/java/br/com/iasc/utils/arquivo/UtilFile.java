package br.com.iasc.utils.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import br.com.iasc.utils.exception.InfraException;

public class UtilFile implements Serializable {

	private static final long serialVersionUID = -7103884554416340363L;

	private static final String SEPARADOR_LINHA = "\r\n";
	private static final String MSG_ERRO_FILE = "Para gerar arquivo é necessário o nome e um byte array válidos!";

	public static File getTempDirectory() {
		return new File(getTempDirectoryPath());
	}

	public static String getTempDirectoryPath() {
		String caminho = System.getProperty("java.io.tmpdir");
		return caminho + File.separator;
	}

	public static Integer contarQuantidadeLinhas(File file) throws IOException {
		if (file != null) {
			return FileUtils.readLines(file).size();
		}
		return 0;
	}

	public static File gerarArquivo(String nomeArquivo, byte[] arquivo) throws IOException {

		if (StringUtils.isBlank(nomeArquivo) || arquivo == null || arquivo.length == 0) {
			throw new IllegalArgumentException(MSG_ERRO_FILE);
		}
		File file = new File(getTempDirectory(), nomeArquivo);

		FileUtils.writeByteArrayToFile(file, arquivo);
		return file;

	}

	public static byte[] getArquivoBytes(File file) throws IOException {
		return FileUtils.readFileToByteArray(file);
	}

	public static int getTamanhoFile(File file) {
		return (int) FileUtils.sizeOf(file);
	}

	public static void escreverArquivo(String caminho, String texto) throws InfraException {
		try {

			File arquivo = new File(caminho);
			arquivo.createNewFile();

			// se o diretorio existir, ele apenas altera o arquivo
			FileWriter writer = new FileWriter(arquivo);
			writer.write(texto);
			writer.close();

		} catch (IOException e) {
			throw new InfraException(e);
		}
	}

	public static boolean renomearDiretorio(String diretorioOriginal, String novoDiretorio) throws InfraException {

		boolean resultado = false;
		try {

			File arquivoOriginal = new File(diretorioOriginal);
			File novoArquivo = new File(novoDiretorio);

			resultado = arquivoOriginal.renameTo(novoArquivo);

		} catch (Exception e) {
			throw new InfraException(e);
		}

		return resultado;
	}

	public boolean excluirDiretorio(File diretorio) {

		if (diretorio.isDirectory()) {

			String[] children = diretorio.list();
			for (int i = 0; i < children.length; i++) {
				boolean sucesso = excluirDiretorio(new File(diretorio, children[i]));
				if (!sucesso) {
					return false;
				}
			}
		}
		return diretorio.delete();
	}

	public static void limparDiretorio(String caminhoDiretorio) {

		File diretorio = new File(caminhoDiretorio);
		if (diretorio.isDirectory()) {

			File[] files = diretorio.listFiles();
			for (File file : files) {
				file.delete();
			}

		}
	}

	public static boolean criarDiretorio(String caminhoDiretorio) {
		File diretorio = new File(caminhoDiretorio);
		if (diretorio.isDirectory()) {
			return false;
		} else {
			diretorio.mkdir();
			return true;
		}
	}

	public static boolean verificarDiretorio(String caminhoDiretorio) {
		File diretorio = new File(caminhoDiretorio);
		return diretorio.isDirectory();
	}

	public static boolean verificarArquivo(String caminhoArquivo) throws Exception {

		File diretorio = new File(caminhoArquivo);
		return diretorio.isFile();
	}

	public static String lerArquivo(String caminho) throws InfraException {
		try {

			StringBuilder texto = new StringBuilder();
			File arquivo = new File(caminho);
			BufferedReader br = new BufferedReader(new FileReader(arquivo));
			while (br.ready()) {
				texto.append(br.readLine() + SEPARADOR_LINHA);
			}

			br.close();
			return texto.toString();

		} catch (IOException e) {
			throw new InfraException(e);
		}
	}

	/**
	 * Retorna o tamanho de um arquivo em um texto "human-readable"
	 * 
	 * @param size
	 * @return
	 */
	public static String readableFileSize(long size) {
		if (size <= 0) {
			return "0";
		}
		final String[] units = new String[] { "Bytes", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	public static long sizeOf(File file) {

		if (!file.exists()) {
			throw new IllegalArgumentException(file + " não existe.");
		}

		return file.length();
	}

}
