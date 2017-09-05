package br.com.iasc.utils.arquivo;

import com.google.common.net.MediaType;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;

public class UtilUpload {

	/**
	 * Veirifica se o arquivo possui o mimeType correto de acordo com a lista de MediaType passada
	 * @param file
	 * @param mediasType
	 * @return
	 * @throws IOException
	 */
	public static boolean isTipoArquvioValido(InputStream file, String nomeArquivo, MediaType... mediasType) throws IOException {
		Tika tika = new Tika();
		for (MediaType mediaType : mediasType) {
			if (isTipoArquvioValido(tika, file, nomeArquivo, mediaType)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Veirifica se o arquivo possui o mimeType correto de acordo com a MediaType passada
	 * @param file
	 * @param mediasType
	 * @return
	 * @throws IOException
	 */
	public static boolean isTipoArquvioValido(InputStream file, String nomeArquivo, MediaType mediaType) throws IOException {
		Tika tika = new Tika();
		return isTipoArquvioValido(tika, file, nomeArquivo, mediaType);

	}

	private static boolean isTipoArquvioValido(Tika tika, InputStream file, String nomeArquivo, MediaType mediaType) throws IOException {
		String media = tika.detect(file, nomeArquivo);
		return media.equalsIgnoreCase(mediaType.toString());

	}

	/**
	 * 
	 * @param file
	 * @param nomeArquivo
	 * @return
	 * @throws IOException
	 */
	public static boolean isTipoArquivoImagemValido(InputStream file, String nomeArquivo) throws IOException {
		return isTipoArquvioValido(file, nomeArquivo, MediaType.BMP, MediaType.JPEG, MediaType.GIF, MediaType.ICO, MediaType.PNG, MediaType.TIFF, MediaType.WEBP);
	}

	/**
	 * 
	 * @param file
	 * @param nomeArquivo
	 * @return
	 * @throws IOException
	 */
	public static boolean isTipoArquivoAudioValido(InputStream file, String nomeArquivo) throws IOException {
		return isTipoArquvioValido(file, nomeArquivo, MediaType.MPEG_AUDIO, MediaType.MP4_AUDIO, MediaType.OGG_AUDIO, MediaType.WEBM_AUDIO);
	}

	/**
	 * 
	 * @param file
	 * @param nomeArquivo
	 * @return
	 * @throws IOException
	 */
	public static boolean isTipoArquivoVideoValido(InputStream file, String nomeArquivo) throws IOException {
		return isTipoArquvioValido(file, nomeArquivo, MediaType.MP4_VIDEO, MediaType.MPEG_VIDEO, MediaType.OGG_VIDEO, MediaType.QUICKTIME, MediaType.WEBM_VIDEO, MediaType.WMV);
	}

}
