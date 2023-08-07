package id.co.sofcograha.domain.modules.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;

public class MenuStructureUtils {
	
	private static final String MENU_INFO_INTERFACE_TS = 
			"interface MenuInfo {\n"
			+ "  menuId: string;\n"
			+ "  menuSequence: string;\n"
			+ "  icon?: string;\n"
			+ "  url?: string;\n"
			+ "}";
	
	private static final String MENU_MASTER_TS = 
			"export const MENU_INFO_MASTER: MenuInfo = {\n"
			+ "  menuId: '0000',\n"
			+ "  menuSequence: '01',\n"
			+ "  icon: 'fa fa-cogs',\n"
			+ "};";
	
	public static <T extends MenuStructureDto> byte[] generateMenuInfoTypescript(
			List<T> menuStructureDtos) throws Exception {
		String script;
		byte[] file;
		
		script = generateMenuInfoScript(menuStructureDtos);
		file = generateMenuInfoFile(script);
		
		return file;
	}
	
	private static <T extends MenuStructureDto> String generateMenuInfoScript(
			List<T> menuStructureDtos) {
		
		StringBuilder sbScript = new StringBuilder();
		sbScript.append(MENU_MASTER_TS);
		sbScript.append("\n\n");
		sbScript.append(
				"export const MENU_INFO_LIST: MenuInfo[] = [\n"
				+ "  MENU_INFO_MASTER,");
		
		for (MenuStructureDto menuStructureDto : menuStructureDtos) {
			sbScript.append("\n");
			sbScript.append("  {\n");
			sbScript.append("    menuId: '" + menuStructureDto.getMenuId() + "',\n");
			sbScript.append("    menuSequence: '" + menuStructureDto.getMenuSequence() + "',\n");
			
			if(menuStructureDto.getIconClass() != null) {
				if(menuStructureDto.getIconClass().length() > 0 
						&& !menuStructureDto.getIconClass().trim().contentEquals("")) {
					sbScript.append("    icon: '" + menuStructureDto.getIconClass() + "',\n");
				}
			}
			
			if(menuStructureDto.getRoutingPath() != null) {
				if(menuStructureDto.getRoutingPath().length() > 0 
						&& !menuStructureDto.getRoutingPath().trim().contentEquals("")) {
					sbScript.append("    url: '" + menuStructureDto.getRoutingPath() + "',\n");
				}
			}
			
			sbScript.append("  },");
		}
		
		sbScript.append("\n];");
		sbScript.append("\n\n");
		sbScript.append(MENU_INFO_INTERFACE_TS);
		
		return sbScript.toString();
	}
	
	private static byte[] generateMenuInfoFile(String pScript) throws Exception {
		File file;
		FileWriter fileWriter;
		FileInputStream fileInputStream;
		byte[] fileBytes;
		
		fileBytes = null;
		try {
			file = new File("menu-info.ts");
			file.createNewFile();
			
			fileWriter = new FileWriter(file);
			fileWriter.write(pScript);
			fileWriter.close();
			
			fileBytes = new byte[(int) file.length()];
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileBytes);
			fileInputStream.close();
		} catch (Exception e) {
			throw e;
		}
		
		return fileBytes;
	}
}
