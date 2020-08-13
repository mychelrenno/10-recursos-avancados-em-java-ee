package br.com.javaEstudo.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String FOLDER_REPORTS = "../reports";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static String SEPARATOR = File.separator;
	private static String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = null;
	private File arquivoGerado = null;

	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar) {

		try {
			// Cria a lista de collectionDataSource de beans que carregam os dados para o relatório
			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);

			// Fornece o caminho físico até a pasta que contém os relatórios .jasper
			String caminhoRelatorio = servletContext.getRealPath("/reports");

			// arquivo jasper
			File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

			if (caminhoRelatorio == null
					|| (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
					|| !file.exists() ) {
				caminhoRelatorio = this.getClass().getResource(FOLDER_REPORTS).getPath();
				SEPARATOR = "";
			}

			// caminho para imagens
			parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

			// caminho completo até o relatório compilado indicado
			String caminhoArquivosJaster = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";

			// faz o carregamento do relatório
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJaster);

			// seta parametros SUBREPORT_DIR com o caminho físico para subreport
			caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
			parametrosRelatorio.put("SUREPORT_DIR", caminhoSubReport_Dir);

			// carrega o arquivo
			JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);

			if (tipoExportar.equalsIgnoreCase("PDF")) {
				exporter = new JRPdfExporter();
			} else if (tipoExportar.equalsIgnoreCase("XLS")) {
				exporter = new JRXlsExporter();
			}

			// caminho do relatorio exportado
			caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + "." + tipoExportar;

			// Criar novo arquivo exportado
			arquivoGerado = new File(caminhoArquivoRelatorio);

			// prepara a impressão
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

			// executa a exportação
			exporter.exportReport();

			// remove o arquivo do servidor após ser feito o donwload
			arquivoGerado.deleteOnExit();
		} catch (JRException e) {
			e.printStackTrace();
		}

		return caminhoArquivoRelatorio;
	}

}
