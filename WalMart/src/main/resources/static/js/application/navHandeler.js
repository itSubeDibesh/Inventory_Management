const blockNavsElements = {
        inventory: `categories,products,warehouse_and_marts,products_management,damage_and_expiry`,
        administration: `users,roles_and_access,login_role_mapping`,
        mart: `items_showcase,billing`,
        members: `customers,vendors`,
        analytics: `purchase_history,sales_history`
    }, navClassList = document.querySelectorAll('.ActivateIndividual'),
    blockNavList = document.querySelectorAll('.ActivateBlock');

navClassList.forEach(navIndividual => {
    //Check If URL Includes Nav Class
    const navClass = navIndividual.classList[1];
    const urlIncludesNav = location.href.includes(navClass.toLowerCase());
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
            if (element.includes(ActiveNav.toLowerCase())&&blockNav.classList[2].toLowerCase() == blockNavKey)
                blockNav.classList.add("active")
        });
    }
}