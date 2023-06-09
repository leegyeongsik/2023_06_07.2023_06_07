import java.util.*;

public class Order {

    int completedNum;
    final Map<Integer, List<Store>> waitdictionary = new HashMap<>();
    final Map<Integer, List<Store>> completedDictionary = new HashMap<>();
    MenuContext menuContext = new MenuContext();

    void displayMainMenu() {
        System.out.println(" BURGER 에 오신걸 환영합니다."); // 메인카테고리 출력
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");
        System.out.println("[ Burger MENU ]");
        int nextNum = printMenu(new MenuContext() ,  1 , "Main");
        System.out.println("[ ORDER MENU ]");
        printMenu(new MenuContext() ,  nextNum , "Order");

        handleMainMenuInput();
    }

     int printMenu(MenuContext menuContext, int num , String Order) {
        List<Menu> menu = menuContext.getMenus(Order);
        for (int i=0; i<menu.size(); i++) {
            System.out.println(num++ + ". " + menu.get(i).name + "   | " + menu.get(i).description);
        }
        return num;
    }

     void handleMainMenuInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("0")) {
            displayAdminMenu();
        } else if (input.equals("Order")) {
            displayOrderMenu();
        } else if (input.equals("Cancel")) {
            handleCancelMenuInput();
        } else {
            MenuContext menuContext = new MenuContext();
            if (menuContext.getMenuItems(input) != null) {
                displayMenu(new MenuContext(), input);

            } else if (input.equals("끝")) {
                System.out.println("끝");
            } else {
                System.out.println("찾는게없음");
                displayMainMenu();
            }
        }
    }

    // 관리자 페이지 로드
     void displayAdminMenu() {
         Scanner scanner = new Scanner(System.in);

         System.out.println("이곳은 관리자 페이지입니다.");
         System.out.println("1. 대기주문 목록");
         System.out.println("2. 완료주문 목록");
         System.out.println("3. 상품 생성");
         System.out.println("4. 상품 삭제");
         System.out.println("5. 주문 현황");
         System.out.println("항목을 선택하세요: ");

         int input = scanner.nextInt();
         switch (input) {
             case 1:
                 displayWaitingOrder(); // 주문대기
                 break;
             case 2:
                 printCompletedOrder(); // 완료주문
                 break;
             case 3:
//                 createItem(); // 상품생성
                 break;
             case 4:
//                 deleteItem(); // 상품삭제
                 break;
             case 5:
                 printCompletedOrderANDWaitingOrder(); // 주문현황
                 break;
             default:
                 System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                 displayAdminMenu();
                 break;
         }
     }





 //1. 대기 중인 주문 조회 및 완료 화면
    void printCompletedOrderANDWaitingOrder(){
        if(waitdictionary.isEmpty()) {
            System.out.println("대기 중인 주문이 없습니다.");
            System.out.println("========================================");
        } else {
            System.out.println("========================================");
            System.out.println("대기 중인 주문 목록입니다.\n");
            System.out.println("[ 대기 주문 목록 ]");
            Set<Integer> set = waitdictionary.keySet();
            Iterator<Integer> iterator = set.iterator();
            while(iterator.hasNext()){
                int key = iterator.next();
                List<Store> wait = waitdictionary.get(key);
                System.out.println("========================================");
                System.out.println(key+"번째 대기주문");
                for (int j = 0; j < wait.size(); j++) {
                    System.out.println("========================================");
                    System.out.println("주문품목");
                    for (int k = 0; k < wait.get(j).menu.size(); k++) {
                        System.out.printf(wait.get(j).menu.get(k) + " ");
                    }
                    System.out.println();
                    System.out.println("주문날짜");
                    System.out.println(wait.get(j).data);
                    System.out.println("주문금액");
                    System.out.println(wait.get(j).TotalNum);
                    System.out.println("요청사항");
                    System.out.println(wait.get(j).request);
                    System.out.println("========================================");
                }
            }
        }
        if (completedDictionary.size() > 0){
            Set<Integer> set = completedDictionary.keySet();

            int tempSlicingNUM = set.size()-3;
            if (tempSlicingNUM < 0){
                tempSlicingNUM = 0;
            }
            ArrayList<Integer> list = new ArrayList<>(set);
            List<Integer> SlicingArr = list.subList(tempSlicingNUM, list.size());
            Iterator<Integer> iterator = SlicingArr.iterator();
            while(iterator.hasNext()){
                int key = iterator.next();
                List<Store> wait = completedDictionary.get(key);
                System.out.println("========================================");
                System.out.println(key+"번째 완료주문");
                for (int j = 0; j < wait.size(); j++) {
                    System.out.println("========================================");
                    System.out.println("완료된주문품목");
                    for (int k = 0; k < wait.get(j).menu.size(); k++) {
                        System.out.printf(wait.get(j).menu.get(k) + " ");
                    }
                    System.out.println();
                    System.out.println("완료처리날짜");
                    System.out.println(wait.get(j).data);
                    System.out.println("완료된주문금액");
                    System.out.println(wait.get(j).TotalNum);
                    System.out.println("완료된요청사항");
                    System.out.println(wait.get(j).request);
                    System.out.println("========================================");
                }
            }
            System.out.println("========================================");
            displayMainMenu();
        } else {
            System.out.println("없다");
            displayMainMenu();
        }

    }
     void displayWaitingOrder() {
         if(waitdictionary.isEmpty()){
            System.out.println("대기 중인 주문이 없습니다.");
            System.out.println("========================================");
            displayMainMenu();
         } else {
             System.out.println("========================================");
             System.out.println("대기 중인 주문 목록입니다.\n");
             System.out.println("[ 대기 주문 목록 ]");
             Set<Integer> set = waitdictionary.keySet();
             Iterator<Integer> iterator = set.iterator();
             while(iterator.hasNext()){
                 int key = iterator.next();
                 List<Store> wait = waitdictionary.get(key);
                 System.out.println("========================================");
                 System.out.println(key+"번째 대기주문");
                 for (int j = 0; j < wait.size(); j++) {
                     System.out.println("========================================");
                     System.out.println("주문품목");
                     for (int k = 0; k < wait.get(j).menu.size(); k++) {
                         System.out.printf(wait.get(j).menu.get(k) + " ");
                     }
                     System.out.println();
                     System.out.println("주문날짜");
                     System.out.println(wait.get(j).data);
                     System.out.println("주문금액");
                     System.out.println(wait.get(j).TotalNum);
                     System.out.println("요청사항");
                     System.out.println(wait.get(j).request);
                     System.out.println("========================================");
                 }
             }
             System.out.println("주문을 처리하시겠습니까?");
             System.out.println("처리:0 , 취소 : 1");
             Scanner scanner = new Scanner(System.in);
             int okno = scanner.nextInt();
//             Set setRemove = waitdictionary.keySet();
             Iterator<Integer> iteratorRemove = set.iterator();
             if (okno ==  0){
                 while(iteratorRemove.hasNext()){
                    int key = iteratorRemove.next();
                     completedNum+=1;
                     List<Store> wait= waitdictionary.get(key);
                     wait.get(0).Setdata(new Date());
                     completedDictionary.put(completedNum,waitdictionary.get(key));
                     waitdictionary.remove(key);
                     break;
                 }
             } else {
                 displayMainMenu();
             }
         }
         displayMainMenu();
     }

     void printCompletedOrder() {
         if(completedDictionary.isEmpty()){
             System.out.println("대기 중인 주문이 없습니다.");
         } else {
             System.out.println("처리 완료된 주문 목록입니다.\n");
             System.out.println("[ 완료 주문 목록 ]");
             Set<Integer> set = completedDictionary.keySet();
             Iterator<Integer> iterator = set.iterator();
             while(iterator.hasNext()){
                 int key = iterator.next();
                 List<Store> wait = completedDictionary.get(key);
                 System.out.println("========================================");
                 System.out.println(key+"번째 완료주문");
                 for (int j = 0; j < wait.size(); j++) {
                     System.out.println("========================================");
                     System.out.println("완료된주문품목");
                     for (int k = 0; k < wait.get(j).menu.size(); k++) {
                         System.out.printf(wait.get(j).menu.get(k) + " ");
                     }
                     System.out.println();
                     System.out.println("완료처리날짜");
                     System.out.println(wait.get(j).data);
                     System.out.println("완료된주문금액");
                     System.out.println(wait.get(j).TotalNum);
                     System.out.println("완료된요청사항");
                     System.out.println(wait.get(j).request);
                     System.out.println("========================================");
                 }
             }
         }
         System.out.println("========================================");
         displayMainMenu();
     }
 //3. 상품 삭제
     void deleteItem() {

    }

    // 4. 상품 생성
     void createItem() {


    }



    void displayMenu(MenuContext menuContext , String menu) {
        System.out.println(" BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        System.out.println("["+ " " + menu +  "MENU ]");
        List<Item> Items = menuContext.getMenuItems(menu);
        printMenuItems(Items);
        handleMenuItemInput(Items);
    }

     void handleMenuItemInput(List<Item> items) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input > 0 && input <= items.size()) {
            input--;
            Item selectedItem = items.get(input);
            displayConfirmation(selectedItem);
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleMenuItemInput(items);
        }
    }

    void printMenuItems(List<Item> items) {
        for (int i=0; i<items.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + items.get(i).name + "   | " + items.get(i).price + " | " + items.get(i).description);
        }
    }


     void displayConfirmation(Item menuItem) {
        System.out.println(menuItem.name + "   | " + menuItem.price + " | " + menuItem.description);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        handleConfirmationInput(menuItem);
    }

    void handleConfirmationInput(Item menuItem) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            menuContext.addToCart(menuItem);
            System.out.println("장바구니에 추가되었습니다.");
            displayMainMenu();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleConfirmationInput(menuItem);
        }
    }

    void displayOrderMenu() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        menuContext.displayCart();

        System.out.println("[ Total ]");
        System.out.println("W " + menuContext.getTotalPrice() + "\n");
        System.out.println("1. 주문      2. 메뉴판");

        handleOrderMenuInput();
    }

    void handleOrderMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            displayOrderComplete();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleOrderMenuInput();
        }
    }

 //주문시 요청 사항 입력받기
      void displayOrderComplete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("주문시 요청사항 메시지 : ");
        String request = scanner.nextLine();

        int orderNumber = menuContext.generateOrderNumber(); // 주문넘버에서 부터 +=1
        System.out.println("주문이 완료되었습니다!\n");
        //
        System.out.println("대기번호는 [ " + orderNumber + " ] 번 입니다.");
        setWaitingOrder(request);

        resetCartAndDisplayMainMenu();
    }
 //주문한 내역 대기 주문 리스트에 입력하기
      void setWaitingOrder(String request) {

        Date now = new Date();
        Store store=new Store();
        for(Item its : menuContext.getCart()){
            store.Setmenu(its);
        }
        store.Setdata(now);
        store.SetTotalNum(menuContext);
        store.Setequest(request);
        List<Store> TempStore = new ArrayList<>();
        TempStore.add(store);
        waitdictionary.put((int) store.TotalNum,TempStore);

    }
    void resetCartAndDisplayMainMenu() {
        menuContext.resetCart();
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        try {
            Thread.sleep(100); // 3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayMainMenu();
    }

    void handleCancelMenuInput() {
        System.out.println("주문을 취소하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        handleCancelConfirmationInput();
    }

    void handleCancelConfirmationInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            menuContext.resetCart();
            System.out.println("주문이 취소되었습니다.");
            displayMainMenu();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleCancelConfirmationInput();
        }
    }
}

