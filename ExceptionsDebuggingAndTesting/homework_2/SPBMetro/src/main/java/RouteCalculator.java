import core.Station;

import java.util.*;

public class RouteCalculator {
    private final StationIndex stationIndex;

    private static final double INTER_STATION_DURATION = 2.5;
    private static final double INTER_CONNECTION_DURATION = 3.5;

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    public List<Station> getShortestRoute(Station from, Station to) {
        List<Station> route = getRouteOnTheLine(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithOneConnection(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);
            return route;
    }

    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;
        for (int i = 0; i < route.size(); i++) {
            Station station = route.get(i);
            if (i > 0) {
                duration += previousStation.getLine().equals(station.getLine()) ?//если предыдущая линия находится на той же линии которую получили из цикла, то
                        INTER_STATION_DURATION : INTER_CONNECTION_DURATION;//это приравниваем к переменной duration 2,5 минуты иначе 3,5 минуты
            }
            previousStation = station;
        }
        return duration;//возвращаем полученный результат
    }

    private List<Station> getRouteOnTheLine(Station from, Station to) {//получить маршрут без пересадок
        if (!from.getLine().equals(to.getLine())) {//если станции отправления не находится на той же линии, что и станции прибывания
            return null;//то возвращаем null
        }
        List<Station> route = new ArrayList<>();//в противном случае создаем список
        List<Station> stations = from.getLine().getStations();//в этот список кладем станцию отправления
        int direction = 0;
        for (Station station : stations) {//проходимся по нашему списку
            if (direction == 0) {
                if (station.equals(from)) {//если список уже содержит такую станцию отправления
                    direction = 1;//то задаем нашей переменной значение 1
                } else if (station.equals(to)) {//если же список содержит уже такую станцию назначения
                    direction = -1;// то нашей переменной задаем значение -1
                }
            }

            if (direction != 0) {//далее выполняем эту проверку, если наша переменная не == 0
                route.add(station);//то кладем в наш список route эту станцию
            }

            if ((direction == 1 && station.equals(to)) ||//затем проверяем если наша переменная == 1 и если эта станция (получается from) такая же, как и станция назначения
                    (direction == -1 && station.equals(from))) {//либо наша переменная == -1 и эта станция (получается to) такая же, как и станция отправления
                break;//то прерываем нашу программу
            }
        }
        if (direction == -1) {// проверяем если переменная == -1
            Collections.reverse(route);//то реверсируем нашу кол-цию (пример: было 123 стало 321)
        }
        return route;//возвращаем список  (в который получается, положили две станции или одну?)
    }

    private List<Station> getRouteWithOneConnection(Station from, Station to) {//получить маршрут с одной пересадкой
        if (from.getLine().equals(to.getLine())) {//если отправление находится на одной линии с назначением
            return null;//то возвращаем null
        }

        List<Station> route = new ArrayList<>();//если все ок то создаем список

        List<Station> fromLineStations = from.getLine().getStations();//В 1 список попадают станции 1 линии до пересадки
        List<Station> toLineStations = to.getLine().getStations();//во 2 список попадают станции 2 линии от пересадки до конечной
        for (Station srcStation : fromLineStations) {
            for (Station dstStation : toLineStations) {
                if (isConnected(srcStation, dstStation)) {//Если они имеют соединение складываем станции в список way
                    ArrayList<Station> way = new ArrayList<>();//создаем список way
                    way.addAll(getRouteOnTheLine(from, srcStation));//и кладем в него 2 станции отправления которые нам вернул метод getRouteOnTheLine
                    way.addAll(getRouteOnTheLine(dstStation, to));//кладем в него 2 станции назначения которые нам вернул метод getRouteOnTheLine
                    if (route.isEmpty() || route.size() > way.size()) {
                        route.clear();
                        route.addAll(way);//потом из него перекладываем в route предварительно его отчистив
                    }
                }
            }
        }
        return route;
    }

    private boolean isConnected(Station station1, Station station2) {
        Set<Station> connected = stationIndex.getConnectedStations(station1);//кладем в созданный список connected -  station1,которая предварительно прошла проверку в getConnectedStations
        return connected.contains(station2);//проверяем содержится ли в списке connected - station2 и возвращаем либо true, либо false (то есть соединение true или нет false)
    }

    private List<Station> getRouteViaConnectedLine(Station from, Station to) {//получить маршрут по соединенной линии (то есть возможно ли сделать пересадку)
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);//в этот список попадают станции 1 линии до пересадки, прошедшие проверку в методе getConnectedStations
        Set<Station> toConnected = stationIndex.getConnectedStations(to);//в этот список попадают станции 2 линии от пересадки до конечной, прошедшие проверку в методе getConnectedStations
        for (Station srcStation : fromConnected) {
            for (Station dstStation : toConnected) {
                if (srcStation.getLine().equals(dstStation.getLine())) {//проверяем если станции 1-й линии пересекается со станциями 2-й линией dstStation
                    return getRouteOnTheLine(srcStation, dstStation);//то возвращаем эти обе станции
        }
    }
        } return null;//если же нет, то возвращаем null
    }
    private List<Station> getRouteWithTwoConnections(Station from, Station to) {//получаем маршрут с двумя пересадками
        if (from.getLine().equals(to.getLine())) {//если точка отправки находится на той же линии, что и точка прибывания
            return null;//то возвращаем null
        }
        ArrayList<Station> route = new ArrayList<>();//в противном случае создаем маршрут

        List<Station> fromLineStations = from.getLine().getStations();//в этот список попадают станции 1 линии до пересадки
        List<Station> toLineStations = to.getLine().getStations();//в этот список попадают станции 2 линии от пересадки до конечной

        for (Station srcStation : fromLineStations) {
            for (Station dstStation : toLineStations) {
                List<Station> connectedLineRoute =//далее складываем станции в созданный список connectedLineRoute
                        getRouteViaConnectedLine(srcStation, dstStation);//(если эти линии пересекаются, а если нет, то получится положим null в список)

                if (connectedLineRoute == null) {//проверяем: если полученные данные == null (а это может случиться, Почему смотри (в методе getRouteViaConnectedLine)
                    continue;//то переходим(возвращаемся) к следующей итерации цикла
                }                                      //как все ок то мы
                List<Station> way = new ArrayList<>();//создаем новый список way
                //if (getRouteOnTheLine(from, srcStation) != null) {
                    way.addAll(getRouteOnTheLine(from, srcStation));//кладем в way: 2 станции отправления которые прошли проверку в методе getRouteOnTheLine (проверку, чтобы быть на одной линии)
                    //way.addAll(Objects.requireNonNull(getRouteOnTheLine(from, srcStation)));
                    way.addAll(connectedLineRoute);//добавляем в него обе станции srcStation, dstStation из списка connectedLineRoute
                    way.addAll(getRouteOnTheLine(dstStation, to));//кладем в way: 2 станции назначения которые, прошли проверку в методе getRouteOnTheLine (проверку, чтобы быть на одной линии)
                    //way.addAll(Objects.requireNonNull(getRouteOnTheLine(dstStation, to)));
               // }
                if (route.isEmpty() || route.size() > way.size()) {//проверяем если список route пуст или его размер > чем список way
                    route.clear();//то чистим список route
                    route.addAll(way);//и вносим в него все что есть в списке way
                }
            }
        } return route;//возвращаем список

    }
}
