package ohos_app_cangjie_entry

import ohos.ability.Ability

let ENTRYABILITY_REGISTER_RESULT = Ability.registerCreator("EntryAbility", {=> MainAbility()})
