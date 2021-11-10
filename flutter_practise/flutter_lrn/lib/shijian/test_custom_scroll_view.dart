
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TestCustomScrollViewWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestCustomScrollViewState();
}

class TestCustomScrollViewState extends State<TestCustomScrollViewWidget> {
  Widget getPagedView(String content){
    return Container(
      color: Colors.green[200],
      child: Center(
        child: Text(content),
      ),
    );
  }

  // 构建 header
  Widget buildHeader(int i) {
    return Container(
      color: Colors.lightBlue.shade200,
      alignment: Alignment.centerLeft,
      child: Text("PersistentHeader $i"),
    );
  }

  @override
  Widget build(BuildContext context) {
    var list = <String>[
      "pageView1",
      "pageView2",
      "pageView3",
    ];
    return Material(
      child: CustomScrollView(
        slivers: [
          SliverAppBar(
            pinned: true,// 滑动到顶端时会固定住
            expandedHeight: 250.0,
            flexibleSpace: FlexibleSpaceBar(
              title: Text("CustomScrollView"),
              background: Image.asset(
                "pics/bg.jpeg",
                fit: BoxFit.cover,
              ),
            ),
          ),

          SliverToBoxAdapter(
            child: SizedBox(
              height: 150,
              child: PageView(
                children: list.map((e) => getPagedView(e)).toList(),
              ),
            ),
          ),
          
          SliverPersistentHeader(
            pinned: true,
            delegate: MySliverHeadDelegate.fixedHeight(
                height: 80,
                child: buildHeader(1))
          ),
          
          SliverPadding(
            padding: EdgeInsets.all(8.0),
            sliver: SliverGrid(
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                mainAxisSpacing: 10.0,
                crossAxisSpacing: 10.0,
                childAspectRatio: 4.0,
              ),
              delegate: SliverChildBuilderDelegate((ctx,index) {
                    return Container(
                      alignment: Alignment.center,
                      color: Colors.cyan[100 * (index % 19)],
                      child: Text('grid item $index'),
                    );
                  },
                childCount: 20
              ),
            ),
          ),

          SliverPersistentHeader(
            pinned: true,
            delegate: MySliverHeadDelegate(
              maxHeight: 80,
              minHeight: 50,
              child: buildHeader(2),
            ),
          ),

          SliverFixedExtentList(
              delegate: SliverChildBuilderDelegate((ctx,index){
                //创建列表项
                return Container(
                  alignment: Alignment.center,
                  color: Colors.lightBlue[100 * (index % 9)],
                  child: Text('list item $index'),
                );},
                childCount: 20
              ),
              itemExtent: 50,
          ),

        ],
      ),
    );
  }
}


typedef SliverHeaderBuilder = Widget Function(
    BuildContext context, double shrinkOffset, bool overlapsContent);

class MySliverHeadDelegate extends SliverPersistentHeaderDelegate {
  final double maxHeight;
  final double minHeight;
  final SliverHeaderBuilder builder;


  MySliverHeadDelegate({
    required this.maxHeight,
    this.minHeight = 0,
    required Widget child,
  })  : builder = ((a, b, c) => child),
        assert(minHeight <= maxHeight && minHeight >= 0);


  //最大和最小高度相同
  MySliverHeadDelegate.fixedHeight({
    required double height,
    required Widget child,
  })  : builder = ((a, b, c) => child),
        maxHeight = height,
        minHeight = height;

  //需要自定义builder时使用
  MySliverHeadDelegate.builder({
    required this.maxHeight,
    this.minHeight = 0,
    required this.builder,
  });

  @override
  Widget build(BuildContext context, double shrinkOffset, bool overlapsContent) {
    //测试代码：如果子组件设置了key，则打印日志
    // if (child.key != null) {
    //   print('${child.key}: shrink: $shrinkOffset，overlaps:$overlapsContent');
    // }
    // 让 header 尽可能充满限制的空间；宽度为 Viewport 宽度，
    // 高度随着用户滑动在[minHeight,maxHeight]之间变化。
    return SizedBox.expand(
      child: builder(context, shrinkOffset, overlapsContent),
    );
  }

  @override
  double get maxExtent => maxHeight;

  @override
  double get minExtent => minHeight;

  @override
  bool shouldRebuild(covariant SliverPersistentHeaderDelegate old) {
    return old.maxExtent != maxExtent || old.minExtent != minExtent;
  }

}