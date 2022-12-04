#include <iostream>
#include <algorithm>
#include <string>

int main(void) {
	int num;
	std::string name;
	std::string color;
	std::cin >> num;
	do
    {
        std::getline(std::cin, name);
    } while (name.length() == 0);
    	//std::cout << name;
	std::cin >> color;
	if ((int)color.length() != num) {
		exit(1);
	}
	int i = 0;
	int j = 0;
	bool flag = false;
	int res = 0;
	while(j < num) {
		while (name[i] != ' ') {
			if (j == num)
				break;
			if (color[j] == color[j+1]) {
				flag = true;
			}
			i++;
			j++;
		}
		if (name[i] == ' ')
			i++;
		if (flag == true) {
			res++;
			flag = false;
		}
	}
	std::cout << res << std::endl;
	return 0;
}


// #include <iostream>
// #include <algorithm>
// #include <string>

// int main(void) {
// 	int num;
// 	std::string name;
// 	std::string color;
// 	std::cout << "Количество букв в названии отдела: " << std::endl;
// 	std::cin >> num;
// 	std::cout << "Название отдела: ";
// 	 do
//     {
//         std::getline(std::cin, name);
//     } while (name.length() == 0);
//     	//std::cout << name;
// 	std::cout << "Цвет букв: " << std::endl;;
// 	std::cin >> color;
// 	if (color.length() != num) {
// 		std::cout << "Количество букв и раскрашенных букв не совпадает" << std::endl;
// 		exit(1);
// 	}
// 	int i = 0;
// 	int j = 0;
// 	bool flag = false;
// 	int res = 0;
// 	while(j < num) {
// 		while (name[i] != ' ') {
// 			if (j == num)
// 				break;
// 			if (color[j] == color[j+1]) {
// 				flag = true;
// 			}
// 			i++;
// 			j++;
// 		}
// 		if (name[i] == ' ')
// 			i++;
// 		if (flag == true) {
// 			res++;
// 			flag = false;
// 		}
// 	}
// 	std::cout << res << std::endl;
// 	return 0;
// }
