#include <iostream>
#include <String>
int main(int argc, char *argv[]) {
    int var_a = 0;
    const int const_a = 0;
    const std::string const_b = "Please insert a number between [1-5] (inclusive):";
    do {
        std::cout << const_b;
        std::cin >> var_a;    
    } while (var_a <= const_a);
    
    do {
        var_a = var_a -1;
        std::cout << var_a << "\n";
    } while (var_a > const_a);
}