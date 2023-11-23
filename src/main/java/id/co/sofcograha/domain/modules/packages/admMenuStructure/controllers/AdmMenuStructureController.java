package id.co.sofcograha.domain.modules.packages.admMenuStructure.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.admMenuStructure.services.AdmMenuStructureService;
import id.co.sofcograha.domain.responses.HttpCustomResponse;

@RestController
@RequestMapping(value = "adm-menu-structure")
public class AdmMenuStructureController {

    @Autowired
    AdmMenuStructureService service;

    @PostMapping
    public HttpCustomResponse findAllParentAndChildMenuWithSettings(
            @RequestBody List<MenuStructureDto> menuStructureDtos,
            HttpServletRequest httpServletRequest) {

        Map<String, List<MenuStructureDto>> result =
                service.findAllParentAndChildMenuWithSettings(
                        menuStructureDtos,
                        httpServletRequest.getHeader("sessionId"));

        return new HttpCustomResponse(result);
    }

    @GetMapping(value = "/all")
    public HttpCustomResponse getAll() {
        return new HttpCustomResponse(service.getAll());
    }

    @PostMapping(value = "/existing-menus")
    public HttpCustomResponse findAllMenuWithExistingSettings(
            @RequestBody Map<String, List<MenuStructureDto>> menuData) {

        List<MenuStructureDto> result =
                service.findAllMenuWithExistingSettings(
                        menuData.get("modulUrlInfoList"),
                        menuData.get("menuInfoList")
                );

        return new HttpCustomResponse(result);
    }

    @PostMapping(value = "/file")
    public HttpCustomResponse createMenuInfoTSFile(
            @RequestBody Map<String, List<MenuStructureDto>> menuData) {

        byte[] file = service.createMenuInfoTSFile(
                menuData.get("modulUrlInfoList"),
                menuData.get("menuInfoList")
        );

        return new HttpCustomResponse(file);
    }

}
