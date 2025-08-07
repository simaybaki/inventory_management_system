
public class BuildingFactory {

	public Building createBuilding(String buildingType) {
		if (buildingType.equalsIgnoreCase("warehouse")) {
			return new Warehouse();
		} else if (buildingType.equalsIgnoreCase("market")) {
			return new Market();
		} else {
			return null;
		}
	}
}
