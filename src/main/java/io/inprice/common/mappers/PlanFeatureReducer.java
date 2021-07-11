package io.inprice.common.mappers;

import java.util.Map;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import io.inprice.common.info.PlanFeature;
import io.inprice.common.models.Plan;

public class PlanFeatureReducer implements LinkedHashMapRowReducer<Integer, Plan> {

	public void accumulate(Map<Integer, Plan> map, RowView rowView) {
    final Plan plan = 
  		map.computeIfAbsent(
				rowView.getColumn("p_id", Integer.class), id -> rowView.getRow(Plan.class)
			);

    if (rowView.getColumn("f_id", Integer.class) != null) {
        plan.getFeatures().add(rowView.getRow(PlanFeature.class));
    }
  }

}