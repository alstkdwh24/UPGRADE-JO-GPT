import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:responsive_framework/responsive_wrapper.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      designSize: const Size(1080, 2340),
      minTextAdapt: true,
      splitScreenMode: true,
      builder: (context, child) {
        return MaterialApp(
          debugShowCheckedModeBanner: false,

          title: 'JO-GPT',
          theme: ThemeData(
            colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
            useMaterial3: true, // 선택 사항: Material 3 디자인을 쓰고 싶을 때
          ),
          // 여기서 ResponsiveWrapper 적용
          builder: (context, widget) => ResponsiveWrapper.builder(
            widget,
            breakpoints: [
              ResponsiveBreakpoint.resize(320, name: 'SMALL_PHONE'),
              ResponsiveBreakpoint.resize(375, name: 'NORMAL_PHONE'),
              ResponsiveBreakpoint.resize(480, name: 'LARGE_PHONE'),
            ],
          ),

          home: const Center(child: MyHomePage(title: 'JO-GPT')),
        );
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  final orangeColor = Color.fromRGBO(255, 165, 0, 1.0);
  final orange = Color.fromRGBO(246, 234, 216, 1);

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: orangeColor,
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {},
          icon: Icon(Icons.menu, size: 36, color: Colors.black),
        ),
        actions: [
          IconButton(
            onPressed: () {},
            icon: Icon(Icons.account_circle, size: 36, color: Colors.black),
          ),
        ],
        backgroundColor: orangeColor,
        centerTitle: true,
        title: Column(children: [Text(widget.title)]),
      ),
      body: Container(
        color: orange,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.end,
          mainAxisAlignment: MainAxisAlignment.spaceBetween, // 위와 아래로 분리

          children: [
            Container(color: orangeColor),
            bottom(),
          ],
        ),
      ),
    );
  }

  Widget bottom() {
    return SafeArea(
      top: false,
      left: false,
      right: false,
      bottom: true,
      child: Container(
        height: 0.08.sh,
        decoration: BoxDecoration(color: orangeColor),
        child: Row(
          children: [
            SizedBox(height: 150.h, width: 0.02.sw),
            Container(
              height: 150.h,
              width: 0.138.sw,
              decoration: BoxDecoration(
                color: Colors.white,
                border: Border.all(color: Colors.black),
                borderRadius: BorderRadius.circular(50),
              ),

              child: IconButton(onPressed: () {}, icon: Icon(Icons.add)),
            ),
            SizedBox(height: 150.h, width: 0.02.sw),
            Align(
              alignment: Alignment.center,
              child: Container(
                width: 0.79.sw,
                height: 150.h,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10),
                  border: Border.all(color: Colors.black),
                  color: Colors.white,
                ),
                child: Padding(
                  padding: EdgeInsetsGeometry.only(left: 30.w, right: 30.w),
                  child: Expanded(child:Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                        // 이걸 추가!
                        // 핵심 수정!
                    TextField(
                          maxLines: null,
                          expands: true,
                          textAlignVertical: TextAlignVertical.top,
                          decoration: InputDecoration(
                            border: InputBorder.none,
                            hintText: '내용을 입력하세요',
                          ),
                        ),
                        IconButton(
                          onPressed: () {},
                          icon: Icon(Icons.search_rounded),
                          color: Colors.black,
                        ),
                    ],
                  ),)
                ),
              ),
            ),
            SizedBox(width: 0.02.sw, height: 150.h),
          ],
        ),
      ),
    );
  }
}
