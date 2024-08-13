package com.example.utils;

import java.util.HashMap;
import java.util.Map;

import com.example.controllers.Charge;
import com.example.controllers.Tool;
import com.example.utils.Enums.*;

public class Utils {
  private Map<ToolType, Charge> charges;
  private Tool[] tools;

  public Utils() {
    setupCharges();
    setupTools();
  }

  private void setupCharges() {
    charges = new HashMap<>();
    charges.put(ToolType.LADDER, new Charge(1.99, true, true, false));
    charges.put(ToolType.CHAINSAW, new Charge(1.49, true, false, true));
    charges.put(ToolType.JACKHAMMER, new Charge(2.99, true, false, false));
  }

  private void setupTools() {
    tools = new Tool[] {
        createTool(ToolCode.CHNS, ToolType.CHAINSAW, ToolBrand.STIHL),
        createTool(ToolCode.LADW, ToolType.LADDER, ToolBrand.WERNER),
        createTool(ToolCode.JAKD, ToolType.JACKHAMMER, ToolBrand.DEWALT),
        createTool(ToolCode.JAKR, ToolType.JACKHAMMER, ToolBrand.RIDGID)
    };
  }

  private Tool createTool(ToolCode code, ToolType type, ToolBrand brand) {
    Charge charge = charges.get(type);
    return new Tool(
        code,
        type,
        brand,
        charge.getDailyCharge(),
        charge.isWeekdayCharge(),
        charge.isWeekendCharge(),
        charge.isHolidayCharge());
  }

  public Tool getToolByType(ToolType toolType) {
    for (Tool tool : tools) {
      if (tool.getToolType() == toolType) {
        return tool;
      }
    }

    return null;
  }
}
