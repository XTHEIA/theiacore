package kr.sbxt.theia.theiacore.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.flattener.ComponentFlattener;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Comp
{
	private static final ComponentFlattener componentFlattener = ComponentFlattener.textOnly();
	private static final PlainTextComponentSerializer PLAIN_TEXT_COMPONENT_SERIALIZER = PlainTextComponentSerializer.plainText();
	public static final Style plain = Style.style(UniversalColor.WHITE.asTextColor(), TextDecoration.ITALIC.withState(TextDecoration.State.FALSE));
	
	public static String flatten(final Component component)
	{
		//		final var ref = new Object()
		//		{
		//			String msg = "";
		//		};
		//		componentFlattener.flatten(component, (comp) -> ref.msg = comp);
		//		return ref.msg;
		return PLAIN_TEXT_COMPONENT_SERIALIZER.serialize(component);
	}
	
	public static Component chat(ComponentLike sender, ComponentLike msg)
	{
		return Comp.a(sender, Comp.t(" "), msg);
	}
	
	public static Component system(ComponentLike message, boolean isBroadcast)
	{
		return chat(Comp.tc("시스템", UniversalColor.QUASAR).hoverEvent(Comp.t(isBroadcast ? "시스템이 모든 플레이어에게 보낸 메시지" : "시스템이 사용자에게 직접 보낸 메시지")), message);
	}
	
	public static String currentTimeString()
	{
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static Component brackets(ComponentLike comp)
	{
		return Comp.t("[").append(comp).append(Comp.t("]"));
	}
	
	public static Component bracketsWithSpace(ComponentLike comp)
	{
		return Comp.t("[ ").append(comp).append(Comp.t(" ]"));
	}
	
	public static Component property(String propKey, Component propValue)
	{
		return a(tc(propKey, UniversalColor.G_200), tc(" ", UniversalColor.G_100), propValue);
	}
	
	public static Component property(String key, String value, UniversalColor color)
	{
		return property(key, tc(value, color));
	}
	
	public static Component property(String key, String value)
	{
		return property(key, value, UniversalColor.LEGACY_YELLOW);
	}
	
	public static Component property(String key, Object value)
	{
		return property(key, value.toString());
	}
	
	public static final Component space = Component.text(" "), empty = Component.text("");
	
	public static Component a(ComponentLike... components)
	{
		var result = Component.empty();
		for (final var component : components)
		{
			result = result.append(component != null ? component : tc("###", UniversalColor.G_200));
		}
		return result;
	}
	
	public static Component hexColor(int rgb)
	{
		return t("#").append(tc(Integer.toHexString(rgb).toUpperCase(), rgb));
	}
	
	public static Component h(Component comp, Component hover)
	{
		var res = comp;
		res = res.hoverEvent(hover);
		return res;
	}
	
	public static Component click(Component target, Component clickDesc, ClickEvent clickEvent)
	{
		return target.hoverEvent(clickDesc).clickEvent(clickEvent);
	}
	
	
	public static Component[] fromStringLores(String... lores)
	{
		final Component[] _loresComp_ = new Component[lores.length];
		int i = 0;
		for (String lore : lores) { _loresComp_[i++] = Comp.tc(lore, UniversalColor.WHITE); }
		return _loresComp_;
	}
	
	
	public static Component t(String text)
	{
		return tc(text, UniversalColor.WHITE);
	}
	
	public static Component highlight(String format, Object... args)
	{
		final var arr = new Object[args.length];
		for (int i = 0; i < args.length; i++)
		{
			arr[i] = "<yellow>%s</yellow>".formatted(String.valueOf(args[i]));
		}
		return MiniMessage.miniMessage().deserialize(MessageFormat.format(format, arr)).style(plain);
	}
	
	public static Component tc(String text, UniversalColor color)
	{
		if (text == null) text = "N/A";
		return Component.empty().append(Component.text(text, Style.style(color.asTextColor(), TextDecoration.ITALIC.withState(TextDecoration.State.FALSE))));
	}
	
	public static Component tc(String text, int rgb)
	{
		return Component.empty().append(Component.text(text, Style.style(TextColor.color(rgb), TextDecoration.ITALIC.withState(TextDecoration.State.FALSE))));
		
	}
	
	public static Component tcd(String text, UniversalColor color, TextDecoration... decorations)
	{
		final var cmp = Component.text(text, Style.style(color.asTextColor(), decorations));
		return cmp;
	}
	
	//	public static Component rarityTypeName(QuasarElement element)
	//	{
	//		return a(
	//				element.getName(),
	//				t(" ("),
	//				element.getRarity().asRarityComponent(),
	//				t(" "),
	//				element.getType().asTypeComponent(),
	//				t(")")
	//		);
	//	}
	
	public static Component typeName(String type, Component name)
	{
		return Comp.t(type + ": ").append(name);
	}
	
	public static Component mergeLines(List<Component> lines)
	{
		var comp = Component.empty();
		if (lines != null)
		{
			final var size = lines.size();
			for (int i = 0; i < size; i++)
			{
				final Component line = lines.get(i);
				comp = comp.append(line);
				if (i != size - 1)
				{
					comp = comp.append(Component.newline());
				}
			}
		}
		
		return comp;
	}
	
	public static Component[] ts(String... data)
	{
		return Arrays.stream(data).map(Comp::t).toArray(Component[]::new);
	}
	
	public static Component number(Number num)
	{
		return tc(num.toString(), UniversalColor.YELLOW_0);
	}
}
