package id.co.sofcograha.domain.modules.packages.admMenuStructure.repositories;

import id.co.sofcograha.domain.modules.constants.Constans;
import id.co.sofcograha.domain.modules.enums.ModulType;
import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.admMenuStructure.entities.AdmMenuStructure;
import id.co.sofcograha.domain.modules.utils.NativeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdmMenuStructureRepository extends SimpleJpaRepository<AdmMenuStructure, String> {

    @Autowired
    private EntityManager em;

    public AdmMenuStructureRepository(EntityManager em) {
        super(AdmMenuStructure.class, em);
    }

    @SuppressWarnings("unchecked")
    public List<MenuStructureDto> findAllParentMenuWithSettings(String sessionId) {
        try {
            Query query = em.createNativeQuery(
                    "select a.id, a.menu_id, a.menu_sequence, \n"
                            + "		CASE WHEN c.variable IS NOT NULL  then c.variable else "
                            + "		upper(regexp_replace(regexp_replace(trim(b.modul_name),'[%+()\\/]','','g'),'[\\s+-]+','_','g')) END as variable, \n"
                            + "		a.menu_desc, b.id as modul_id , c.icon_class, c.routing_path \n"
                            + "	from adm_menu_structure a \n"
                            + "				inner join adm_modul b \n"
                            + "						on b.id = a.id_adm_modul  \n"
                            + "				left join (\n"
                            + "								select menu_id, menu_sequence, icon_class, routing_path, variable \n"
                            + "									from sg_temp_menu_structure_settings \n"
                            + "									where session_id = :sessionId \n"
                            + "						  ) c \n"
                            + "						on c.menu_id = a.menu_id \n"
                            + "						and c.menu_sequence = a.menu_sequence \n"
                            + "	where a.menu_id = :menuIdMenuUtama \n"
                            + "			and a.menu_sequence != '00' \n"
                            + "	and b.modul_id not in (:modulIdMenuUtama, :modulIdMenuMaster) \n"
                            + "	and b.modul_type = :modulType \n"
                            + "	order by concat(a.menu_id, a.menu_sequence)",
                    MenuStructureDto.class);
            query.setParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA);
            query.setParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
            query.setParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);
            query.setParameter("modulType", ModulType.M.name());
            query.setParameter("sessionId", sessionId);

            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public List<MenuStructureDto> findAllChildMenuWithSettings(String sessionId) {
        try {
            Query query = em.createNativeQuery(
                    "select a.id, a.menu_id, a.menu_sequence, \n"
                            + "		CASE WHEN c.variable IS NOT NULL  then c.variable else "
                            + "		upper(regexp_replace(regexp_replace(trim(b.modul_name),'[%+()\\/]','','g'),'[\\s+-]+','_','g')) END as variable, \n"
                            + "		a.menu_desc, b.id as modul_id, c.icon_class, c.routing_path \n"
                            + "	from adm_menu_structure a \n"
                            + "				inner join adm_modul b \n"
                            + "						on b.id = a.id_adm_modul  \n"
                            + "				left join (\n"
                            + "								select menu_id, menu_sequence, icon_class, routing_path, variable \n"
                            + "									from sg_temp_menu_structure_settings \n"
                            + "									where session_id = :sessionId \n"
                            + "						  ) c \n"
                            + "						on c.menu_id = a.menu_id \n"
                            + "						and c.menu_sequence = a.menu_sequence \n"
                            + "	where a.menu_id like :menuIdMenuUtama \n"
                            + "			and a.menu_sequence != '00' \n"
                            + "	and b.modul_id not in (:modulIdMenuUtama, :modulIdMenuMaster) \n"
                            + "	and b.modul_type = :modulType \n"
                            + "	order by concat(a.menu_id, a.menu_sequence)",
                    MenuStructureDto.class);
            query.setParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA.concat("%"));
            query.setParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
            query.setParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);
            query.setParameter("modulType", ModulType.P.name());
            query.setParameter("sessionId", sessionId);

            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public List<MenuStructureDto> getAll() {
        Query query = em.createNativeQuery(
                "select a.id, a.menu_id, a.menu_sequence, \n" +
                        "       a.menu_desc, b.modul_id, '' as icon_class, \n" +
                        "       '' as routing_path, '' as variable, b.modul_type \n" +
                        "    from adm_menu_structure a \n" +
                        "        inner join adm_modul b on a.id_adm_modul = b.id \n" +
                        "where 1=1 \n" +
                        "  and a.menu_id like :menuIdMenuUtama \n" +
                        "  and a.menu_sequence <> '00'\n" +
                        "  and b.modul_id not in(:modulIdMenuUtama, :modulIdMenuMaster)\n" +
                        "order by a.menu_id || a.menu_sequence ",
                MenuStructureDto.class);
        query.setParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA.concat("%"));
        query.setParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
        query.setParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);

        return query.getResultList();
    }

    public List<MenuStructureDto> findAllMenuWithExistingSettings(
            String pModulUrlInfoList, String pMenuInfoList) {

        NativeQueryBuilder<MenuStructureDto> queryBuilder =
                new NativeQueryBuilder<>(em, MenuStructureDto.class);
        queryBuilder.addParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA.concat("%"));
        queryBuilder.addParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
        queryBuilder.addParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);

        StringBuilder sb = new StringBuilder();
        sb.append("select a.id, a.menu_id, a.menu_sequence, a.menu_desc, b.modul_id, \n");

        if (pMenuInfoList != null && !pMenuInfoList.trim().isEmpty()) {
            sb.append("case when x.routing_path is null then y.icon_class \n" +
                    "           else x.icon_class end as icon_class, \n");
        } else {
            sb.append("x.icon_class, \n");
        }

        sb.append("x.routing_path, case when x.variable is not null then x.variable else \n" +
                "           'MENU_INFO_' ||\n" +
                "       upper(regexp_replace(trim(regexp_replace(trim(b.modul_id || ' ' || b.modul_name), '[^a-z|A-Z|0-9|\\-]', ' ', 'g')), '\\s+|-+',\n" +
                "                            '_', 'g')) \n" +
                "           end as variable, \n" +
                "       b.modul_type \n" +
                "from adm_menu_structure a \n" +
                "        inner join adm_modul b on a.id_adm_modul = b.id \n" +
                "        left join (values " + pModulUrlInfoList + ") \n" +
                "            as x(modul_id, routing_path, variable, icon_class) \n" +
                "            on b.modul_id = x.modul_id \n");

        if (pMenuInfoList != null && !pMenuInfoList.trim().isEmpty()) {
            sb.append("left join (values " + pMenuInfoList + " ) \n" +
                    "            as y(menu_id, menu_sequence, icon_class) \n" +
                    "            on a.menu_id = y.menu_id and a.menu_sequence = y.menu_sequence \n");
        }

        sb.append("where 1=1 \n" +
                "and a.menu_id like :menuIdMenuUtama \n" +
                "and b.modul_id not in(:modulIdMenuUtama, :modulIdMenuMaster) \n" +
                "and a.menu_sequence <> '00' \n" +
                "order by a.menu_id || a.menu_sequence ");

        queryBuilder.setQueryStringBuilder(sb);

        return queryBuilder.getResultList();
    }

}
