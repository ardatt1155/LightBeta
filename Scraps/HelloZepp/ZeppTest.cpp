
#include "Zepp.hpp"
#include  "Catch.hpp"

#ifdef CATCH_CONFIG_MAIN

TEST_CASE("Run Executes", "[run]" ) {
    REQUIRE(Run() == 0);
}

#endif

