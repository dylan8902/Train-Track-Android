package dyl.anjon.es.traintrack.api;

import org.w3c.dom.Element;

import dyl.anjon.es.traintrack.models.Location;
import dyl.anjon.es.traintrack.utils.Utils;

public class ServiceItem {

	private Location origin;
	private Location destination;
	private String scheduledTimeArrival;
	private String estimatedTimeArrival;
	private String scheduledTimeDeparture;
	private String estimatedTimeDeparture;
	private String platform;
	private String operator;
	private String operatorCode;
	private boolean isCircularRoute;
	private String serviceId;

	public ServiceItem(Element ts) {
		if (ts.getElementsByTagName("origin").getLength() > 0) {
			Element orig = (Element) ts.getElementsByTagName("origin").item(0);
			if (orig.getElementsByTagName("crs").getLength() > 0) {
				String crs = orig.getElementsByTagName("crs").item(0)
						.getTextContent();
				Location origin = Location.getByCrs(crs);
				if (origin != null) {
					this.origin = origin;
				} else {
					Utils.log("NO ORIGIN FOUND IN DB!");
				}
			}
		}
		if (ts.getElementsByTagName("destination").getLength() > 0) {
			Element dest = (Element) ts.getElementsByTagName("destination").item(0);
			if (dest.getElementsByTagName("crs").getLength() > 0) {
				String crs = dest.getElementsByTagName("crs").item(0)
						.getTextContent();
				Location destination = Location.getByCrs(crs);
				if (destination != null) {
					this.destination = destination;
				} else {
					Utils.log("NO DESTINATION FOUND IN DB!");
				}
			}
		}
		if (ts.getElementsByTagName("sta").getLength() > 0) {
			this.scheduledTimeArrival = ts.getElementsByTagName("sta").item(0)
					.getTextContent();
		}
		if (ts.getElementsByTagName("eta").getLength() > 0) {
			this.estimatedTimeArrival = ts.getElementsByTagName("eta").item(0)
					.getTextContent();
		}
		if (ts.getElementsByTagName("std").getLength() > 0) {
			this.scheduledTimeDeparture = ts.getElementsByTagName("std")
					.item(0).getTextContent();
		}
		if (ts.getElementsByTagName("etd").getLength() > 0) {
			this.estimatedTimeDeparture = ts.getElementsByTagName("etd")
					.item(0).getTextContent();
		}
		if (ts.getElementsByTagName("platform").getLength() > 0) {
			this.platform = ts.getElementsByTagName("platform").item(0)
					.getTextContent();
		}
		if (ts.getElementsByTagName("operator").getLength() > 0) {
			this.operator = ts.getElementsByTagName("operator").item(0)
					.getTextContent();
		}
		if (ts.getElementsByTagName("operatorCode").getLength() > 0) {
			this.operatorCode = ts.getElementsByTagName("operatorCode").item(0)
					.getTextContent();
		}
		if (ts.getElementsByTagName("isCircularRoute").getLength() > 0) {
			this.isCircularRoute = Boolean.valueOf(ts.getElementsByTagName("isCircularRoute").item(0)
					.getTextContent());
		}
		if (ts.getElementsByTagName("serviceID").getLength() > 0) {
			this.serviceId = ts.getElementsByTagName("serviceID").item(0)
					.getTextContent();
		}
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public String getScheduledTimeArrival() {
		return scheduledTimeArrival;
	}

	public void setScheduledTimeArrival(String scheduledTimeArrival) {
		this.scheduledTimeArrival = scheduledTimeArrival;
	}

	public String getEstimatedTimeArrival() {
		return estimatedTimeArrival;
	}

	public void setEstimatedTimeArrival(String estimatedTimeArrival) {
		this.estimatedTimeArrival = estimatedTimeArrival;
	}

	public String getScheduledTimeDeparture() {
		return scheduledTimeDeparture;
	}

	public void setScheduledTimeDeparture(String scheduledTimeDeparture) {
		this.scheduledTimeDeparture = scheduledTimeDeparture;
	}

	public String getEstimatedTimeDeparture() {
		return estimatedTimeDeparture;
	}

	public void setEstimatedTimeDeparture(String estimatedTimeDeparture) {
		this.estimatedTimeDeparture = estimatedTimeDeparture;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public boolean isCircularRoute() {
		return isCircularRoute;
	}

	public void setCircularRoute(boolean isCircularRoute) {
		this.isCircularRoute = isCircularRoute;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String toString() {
		return this.getScheduledTimeDeparture() + " " + this.getOperator()
				+ " service from " + this.getOrigin() + " to "
				+ this.getDestination() + " on platform " + this.getPlatform();
	}

}