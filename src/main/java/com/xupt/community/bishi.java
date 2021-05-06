package com.xupt.community;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class bishi {
    public static class WeekData {
        // 一周的开始时间
        private LocalDate start;
        // 一周的结束时间
        private LocalDate end;

        public WeekData() {
        }

        public WeekData(List<LocalDate> localDates) {
            this.start = localDates.get(0);
            this.end = localDates.get(localDates.size() - 1);
        }

        public LocalDate getStart() {
            return start;
        }

        public void setStart(LocalDate start) {
            this.start = start;
        }

        public LocalDate getEnd() {
            return end;
        }

        public void setEnd(LocalDate end) {
            this.end = end;
        }

        public WeekData getIns() {
            return new WeekData();
        }

        @Override
        public String toString() {
            return "WeekData{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        List<String> res = new ArrayList<>();
//        String s1 = "Gsy:35000 0 0 0 0 36000 0 0 0 0 0 40000 0 0 0 0 32000";
//        String s2 = "Wj:12000 12000 12000 12000 12000 12000 12000 0 12000 12000 12000 12000 0 12000 12000 12000 12000 12000 12000";
//        String s3 = "Jww:2000";
//        String s4 = "Zzc:6000 6000 6000 6000 0 6000 6000 6000 0 0 6000 6000 6000 6000 6000 6000 6000 6000 6000 6000 6000 6000 6000";
//        String s5 = "Dbw:3000";
//        List<String> list = new ArrayList<>();
//        list.add(s1);
//        list.add(s2);
//        list.add(s3);
//        list.add(s4);
//        list.add(s5);
//        for (String s : list) {
////            String s = sc.nextLine();
//            String name = s.split(":")[0];
//            String[] data1 = s.split(":")[1].split(" ");
//            String[] data = data1;
//            if (data1.length < 22) {
//                data = new String[22];
//                for (int i = 0; i < data1.length; i++) {
//                    data[i] = data1[i];
//                }
//                for (int i = data1.length; i < 22; i++) {
//                    data[i] = "0";
//                }
//            }
//            int allSteps = 0;
//            int threeWD = 0;
//            int firstDay3 = 0;
//            int oneWD = 0;
//            int fiveTD = 0;
//            int badD = 0;
//            for (int i = 0; i < data.length; i++) {
//                int steps = Integer.parseInt(data[i]);
//                if (steps > 30000) {
//                    if (i != 0 && i - firstDay3 <= 4) {
//                        threeWD = 0;
//                    } else {
//                        threeWD++;
//                    }
//                    firstDay3 = i;
//                } else if (steps > 10000) {
//                    oneWD++;
//                } else if (steps > 5000) {
//                    fiveTD++;
//                } else {
//                    badD++;
//                }
//                allSteps += steps;
//            }
//            StringBuffer sb = new StringBuffer();
//            if (threeWD >= 4) {
//                sb.append(name + ":" + "excellent1 " + allSteps);
//            } else if (oneWD >= 15) {
//                sb.append(name + ":" + "excellent2 " + allSteps);
//
//            } else if (fiveTD >= 15) {
//                sb.append(name + ":" + "good " + allSteps);
//
//            } else if (badD >= 18) {
//                sb.append(name + ":" + "bad " + allSteps);
//            }
//            res.add(sb.toString());
//        }
//        List<String> result = new ArrayList<>();
//        for (String re : res) {
//            if (re.contains("excellent1")) {
//                result.add(re);
//            }
//        }
//        for (String re : res) {
//            if (re.contains("excellent2")) {
//                result.add(re);
//            }
//        }
//        for (String re : res) {
//            if (re.contains("good")) {
//                result.add(re);
//            }
//        }
//        for (String re : res) {
//            if (re.contains("bad")) {
//                result.add(re);
//            }
//        }
//        for (String s : result) {
//            s.replace("excellent1","excellent");
//            s.replace("excellent2","excellent");
//            System.out.println(s);
//        }
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

//    public static void main(String[] args) {
//        try {
//            Scanner sc = new Scanner(System.in);
//            String src = sc.nextLine();
//            String[] strings = src.split(" ");
//            int year = Integer.parseInt(strings[0]);
//            int month = Integer.parseInt(strings[1]);
//            int week = Integer.parseInt(strings[2]);
//            int d = Integer.parseInt(strings[3]);
//            int fristDay = Integer.parseInt(weeks(year, month).get(week).start.toString().split("-")[2]);
//            int now = fristDay + d - 1;
//
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            Date dd = null;
//            try {
//                dd = f.parse(weeks(year, month).get(1).start.toString());
//            } catch (ParseException e) {
//                System.out.println(0);
//            }
//            cal.setTime(dd);
//            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//            if (w == 0) w = 7;
//            if (week == 1 && d < w) {
//                System.out.println(0);
//            } else if (week == 1) {
//                LocalDate res = weeks(year, month).get(week).start.plusDays(d - w);
//                System.out.println(res);
//            } else {
//                LocalDate result = weeks(year, month).get(week).start.plusDays(d - 1);
//                if (result.getMonth().getValue() != month) {
//                    System.out.println(0);
//                } else {
//                    System.out.println(result);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(0);
//        }
//
////        System.out.println("开始日期"+new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(year, month, 1);
////        int dayOfWeek = 7 - calendar.get(7);
////        week = week - 2;
////        calendar.add(Calendar.DAY_OF_YEAR, week * 7 + dayOfWeek);
////        System.out.println("开始日期"+new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
////        System.out.println("结束日期"+new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
//    }

    public static Map<Integer, WeekData> weeks(int year, int month) {
        LocalDate start = LocalDate.now().withYear(year).withMonth(month).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = LocalDate.now().withYear(year).withMonth(month).with(TemporalAdjusters.lastDayOfMonth());
        Map<Integer, WeekData> map = Stream.iterate(start, localDate -> localDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                .collect(Collectors.groupingBy(localDate -> localDate.get(WeekFields.of(DayOfWeek.MONDAY, 1).weekOfMonth()),
                        Collectors.collectingAndThen(Collectors.toList(), WeekData::new)));
        return map;
    }

}
