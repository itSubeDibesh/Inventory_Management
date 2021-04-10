const blockNavsElements = {
        inventory: `categories,warehouse_and_marts,products_management,products,damage_and_expiry`,
        administration: `users`,
        mart: `items_showcase,billing`,
        members: `customers,vendors`
    }, navClassList = document.querySelectorAll('.ActivateIndividual'),
    blockNavList = document.querySelectorAll('.ActivateBlock');

navClassList.forEach(navIndividual => {
    //Check If URL Includes Nav Class
    const navClass = navIndividual.classList[1];
    const urlIncludesNav = location.href.toString().split("/")[3]==(navClass.toLowerCase());
    //Setting Active Nav
    if (urlIncludesNav) {
        navIndividual.classList.add("active");
        // Setting Active Nav Block
        AddNavBlockActive(navClass);
    } else
        navIndividual.classList.remove("active");
});


function AddNavBlockActive(ActiveNav) {
    for (const blockNavKey in blockNavsElements) {
        const element = blockNavsElements[blockNavKey];
        blockNavList.forEach(blockNav => {
            if (element.includes(ActiveNav.toLowerCase()) && blockNav.classList[2].toLowerCase() == blockNavKey)
                blockNav.classList.add("active")
        });
    }
}