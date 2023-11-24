package id.co.sofcograha.domain.modules.packages.admMenuStructure.services;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.admMenuStructure.repositories.AdmMenuStructureRepository;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettings;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettingsId;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.services.SgTempMenuStructureSettingsService;
import id.co.sofcograha.domain.modules.utils.MenuStructureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AdmMenuStructureService {

    @Autowired
    AdmMenuStructureRepository repository;
    @Autowired
    SgTempMenuStructureSettingsService sgTempMenuStructureSettingsService;

    @Transactional
    public Map<String, List<MenuStructureDto>> findAllParentAndChildMenuWithSettings(List<MenuStructureDto> menuStructureDtos, String sessionId) {
        Map<String, List<MenuStructureDto>> menus = new HashMap<>();

        sgTempMenuStructureSettingsService.deleteAllBySessionId(sessionId);

        for (MenuStructureDto menuStructureDto : menuStructureDtos) {
            if (menuStructureDto.getMenuId().trim().isBlank() &&
                    menuStructureDto.getMenuSequence().trim().isBlank()) {
                continue;
            }
            SgTempMenuStructureSettingsId menuStructureSettingsId =
                    new SgTempMenuStructureSettingsId(menuStructureDto.getMenuId(),
                            menuStructureDto.getMenuSequence(), sessionId);

            SgTempMenuStructureSettings menuStructureSettings = new SgTempMenuStructureSettings();
            menuStructureSettings.setSgTempMenuStructureSettingsId(menuStructureSettingsId);
            menuStructureSettings.setIconClass(menuStructureDto.getIconClass());
            menuStructureSettings.setRoutingPath(menuStructureDto.getRoutingPath());
            menuStructureSettings.setVariable(menuStructureDto.getVariable());

            sgTempMenuStructureSettingsService.save(menuStructureSettings);
        }

        List<MenuStructureDto> parentMenuStructures = repository.findAllParentMenuWithSettings(sessionId);
        menus.put("parentMenuStructures", parentMenuStructures);
        menus.put("childMenuStructures", repository.findAllChildMenuWithSettings(sessionId));

        sgTempMenuStructureSettingsService.deleteAllBySessionId(sessionId);

        return menus;
    }

    public List<MenuStructureDto> getAll() {
        return repository.getAll();
    }

    public List<MenuStructureDto> findAllMenuWithExistingSettings(
            List<MenuStructureDto> modulUrlInfoList, List<MenuStructureDto> menuInfoList) {
        String pModulUrlInfoList = createParamOfModulUrlInfoList(modulUrlInfoList);
        String pMenuInfoList = createParamOfMenuInfoList(menuInfoList);

        return repository.findAllMenuWithExistingSettings(pModulUrlInfoList, pMenuInfoList);
    }

    @Transactional
    public byte[] createMenuInfoTSFile(
            List<MenuStructureDto> modulUrlInfoList, List<MenuStructureDto> menuInfoList) {
        String pModulUrlInfoList = createParamOfModulUrlInfoList(modulUrlInfoList);
        String pMenuInfoList = createParamOfMenuInfoList(menuInfoList);
        List<MenuStructureDto> completeMenuStruct =
                repository.findAllMenuWithExistingSettings(
                        pModulUrlInfoList, pMenuInfoList);
        byte[] fileBytes = null;

        try {
            fileBytes = MenuStructureUtils.generateMenuInfoTypescript(completeMenuStruct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fileBytes;
    }

    private String createParamOfModulUrlInfoList(List<MenuStructureDto> modulUrlInfoList) {
        StringBuilder pModulUrlInfoList = new StringBuilder();
        Iterator<MenuStructureDto> modulUrlInfoIterator =
                modulUrlInfoList.iterator();

        while (modulUrlInfoIterator.hasNext()) {
            MenuStructureDto modulUrlInfo = modulUrlInfoIterator.next();
            pModulUrlInfoList.append("(");
            pModulUrlInfoList.append(String.format("'%s'", modulUrlInfo.getModulId()));
            pModulUrlInfoList.append(String.format(", '%s'", modulUrlInfo.getRoutingPath()));

            String pVariable = "null";
            if (modulUrlInfo.getVariable() != null && !modulUrlInfo.getVariable().isBlank()) {
                pVariable = String.format("'%s'", modulUrlInfo.getVariable());
            }
            pModulUrlInfoList.append(String.format(", %s", pVariable));

            String pIconClass = "null";
            if (modulUrlInfo.getIconClass() != null && !modulUrlInfo.getIconClass().isBlank()) {
                pIconClass = String.format("'%s'", modulUrlInfo.getIconClass());
            }
            pModulUrlInfoList.append(String.format(", %s", pIconClass));

            pModulUrlInfoList.append(")");

            if (modulUrlInfoIterator.hasNext()) {
                pModulUrlInfoList.append(",");
            }
        }

        return pModulUrlInfoList.toString();
    }

    private String createParamOfMenuInfoList(List<MenuStructureDto> menuInfoList) {
        StringBuilder pMenuInfoList = new StringBuilder();
        Iterator<MenuStructureDto> menuInfoIterator =
                menuInfoList.iterator();

        while (menuInfoIterator.hasNext()) {
            MenuStructureDto menuInfo = menuInfoIterator.next();
            pMenuInfoList.append("(");
            pMenuInfoList.append(String.format("'%s'", menuInfo.getMenuId()));
            pMenuInfoList.append(String.format(", '%s'", menuInfo.getMenuSequence()));

            String pIconClass = "null";
            if (menuInfo.getIconClass() != null && !menuInfo.getIconClass().isBlank()) {
                pIconClass = String.format("'%s'", menuInfo.getIconClass());
            }
            pMenuInfoList.append(String.format(", %s", pIconClass));

            pMenuInfoList.append(")");

            if (menuInfoIterator.hasNext()) {
                pMenuInfoList.append(",");
            }
        }

        return pMenuInfoList.toString();
    }
}
